package com.epam.provider.dao.pool;

import com.epam.provider.util.resource.MessageResourceManager;
import com.epam.provider.util.resource.ResourceConstants;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A class that provides connections for user requests.
 *
 * @author Gleb Aksenov
 */
public class ConnectionPool {

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static AtomicBoolean isAvailable = new AtomicBoolean(false);
    private static AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static ConnectionPool instance;
    private static ReentrantLock instanceLock = new ReentrantLock();
    private ArrayBlockingQueue<Connection> activePool;
    private ArrayBlockingQueue<Connection> givenAwayConnections;
    private int poolSize;

    public static ConnectionPool getInstance() throws ConnectionPoolException {
        if (!isAvailable.get()) {
            try {
                instanceLock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    isAvailable.set(true);
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    /**
     * This constructor serves to initialize the connection parameters to the database.
     */
    private ConnectionPool() throws ConnectionPoolException {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(ResourceConstants.RESOURCE_PATH_DATABASE);
            poolSize = Integer.parseInt(resourceBundle.getString(ResourceConstants.DB_KEY_POOL_SIZE));
            activePool = new ArrayBlockingQueue<>(poolSize);
            givenAwayConnections = new ArrayBlockingQueue<>(poolSize);
        } catch (Exception ex) {
            LOGGER.log(Level.ERROR, ex.getMessage());
            throw new ConnectionPoolException("Can't init connection pool", ex);

        }
    }

    /**
     * This method creates free connections.
     * And also creates list of busy connection.
     *
     * @throws ConnectionPoolException
     */
    public void initPoolData() throws ConnectionPoolException {
        if (activePool.size() + givenAwayConnections.size() == 0) {
            try {
                for (int i = 0; i < poolSize; i++) {
                    activePool.offer(new PooledConnection(ConnectorDB.getConnection()));
                }
                isInitialized.set(true);
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
                throw new ConnectionPoolException("Error initializing connection pool ", e);
            }
        }
    }

    private void checkIfInitialized() throws ConnectionPoolException {
        if (!isInitialized.get()) {
            LOGGER.log(Level.ERROR, "can't initialize connection pool");
            throw new ConnectionPoolException("can't initialize connection pool");
        }
    }

    /**
     * The method provides a free connection.
     *
     * @return {@link Connection}
     * @throws ConnectionPoolException
     */
    public Connection getConnection() throws ConnectionPoolException {
        //checkIfInitialized();
        try {
            Connection connection = activePool.take();
            givenAwayConnections.add(connection);
            return connection;
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ConnectionPoolException("Error getting connection", e);
        }

    }

    /**
     * Method closes all connection
     */
    public void clearConnectionQueue() throws ConnectionPoolException {
        checkIfInitialized();
        if (isAvailable.get()) {
            isAvailable.set(false);
        }
        closeConnectionQueue(givenAwayConnections);
        closeConnectionQueue(activePool);
        isInitialized.set(false);
        LOGGER.log(Level.INFO, "Connection pool was closed");

    }

    public void closeConnectionQueue(ArrayBlockingQueue<Connection> queue) throws ConnectionPoolException {
        Connection connection;
        while ((connection = queue.poll()) != null) {
            try {
                if (!connection.getAutoCommit()) {
                    connection.commit();
                }
                ((PooledConnection) connection).closeConnection();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, MessageResourceManager.getProperty("connection.pool.error.closing"));
                throw new ConnectionPoolException(e.getMessage());
            }
        }

    }

    /**
     * Method of transferring a connection from a statute busy status is free.
     *
     * @param connection
     * @throws InterruptedException
     * @throws ConnectionPoolException
     */
    public void releaseConnection(Connection connection) throws ConnectionPoolException {
        checkIfInitialized();
        try {
            if (connection.isClosed()) {
                throw new SQLException("trying to close closed exception");
            }
            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }
            if (!connection.getAutoCommit()) {
                connection.setAutoCommit(true);
            }
            if (givenAwayConnections.contains(connection)) {
                if (!givenAwayConnections.remove(connection)) {
                    throw new ConnectionPoolException("Error deleting connection from the given connection pool");
                }
            }
            if (!activePool.offer(connection)) {
                throw new ConnectionPoolException("Error allocating connection in the pool");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ConnectionPoolException("Error closing connection", e);
        }
    }
}
