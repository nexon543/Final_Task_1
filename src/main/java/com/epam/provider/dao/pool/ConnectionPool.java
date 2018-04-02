package com.epam.provider.dao.pool;

import com.epam.provider.util.resource.MessageResourceManager;
import com.epam.provider.util.resource.ResourceConstants;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by HP on 26.03.2018.
 */
public class ConnectionPool {
    private static String PATH_TO_DATA = "database";


    private static Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static AtomicBoolean isAvailable = new AtomicBoolean(false);
    private static ConnectionPool instance;
    private static ReentrantLock instanceLock = new ReentrantLock();
    private ArrayBlockingQueue<Connection> activePool;
    private ArrayBlockingQueue<Connection> givenAwayConnections;
    private int poolSize;

    public static ConnectionPool getInstance() {
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


    private ConnectionPool() {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(ResourceConstants.RESOURCE_PATH_DATABASE);
            poolSize = Integer.parseInt(resourceBundle.getString(ResourceConstants.DB_KEY_POOL_SIZE));
            activePool = new ArrayBlockingQueue<>(poolSize);
            givenAwayConnections = new ArrayBlockingQueue<>(poolSize);
            initPoolData();
        } catch (Exception ex) {
            logger.log(Level.ERROR, "Can't init connection pool");
        }
    }

    public void initPoolData() {
        try {
            for (int i = 0; i < poolSize; i++) {
                activePool.offer(new PooledConnection(ConnectorDB.getConnection()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = activePool.take();
            givenAwayConnections.add(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, MessageResourceManager.getProperty("connection.pool.error.taking"));
        }
        return connection;
    }

    public void clearConnectionQueue() {
        closeConnectionQueue(activePool);
        closeConnectionQueue(givenAwayConnections);
    }

    public void closeConnectionQueue(ArrayBlockingQueue<Connection> queue) {
        Connection connection;
        while ((connection = queue.poll()) != null) {
            try {
                if (!connection.getAutoCommit()) {
                    connection.commit();
                }
                ((PooledConnection)connection).closeConnection();
            } catch (SQLException e) {
                logger.log(Level.ERROR, MessageResourceManager.getProperty("connection.pool.error.closing"));
            }
        }
    }

    public void releaseConnection (Connection connection){
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
                    throw new SQLException("Error deleting connection from the given connection pool");
                }
            }
            if (!activePool.offer(connection)) {
                throw new SQLException("Error allocating connection in the pool");
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error closing connection",e);
        }

    }
}
