package com.epam.provider.dao.impl;

import com.epam.provider.dao.DaoException;
import com.epam.provider.dao.TariffDao;
import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.dao.pool.ConnectionPoolException;
import com.epam.provider.model.Tariff;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gleb Akseonov
 */
public class TariffDaoImpl extends AbstractDao<Tariff> implements TariffDao {

    private static final Logger LOGGER = Logger.getLogger(TariffDaoImpl.class);

    private static final String SQL_SP_SELECT_ALL = "{call get_tariffs(?)}";
    private static final String SQL_SP_SELECT_BY_ID = "{call get_tariffs_by_id(?,?)}";
    private static final String SQL_SP_SELECT_LIMITED = "{call get_tariffs_limited(?, ?, ?)}";
    private static final String SQL_SP_INSERT = "{call insert_tariff(?,?,?,?,?,?)}";
    private static final String SQL_SP_UPDATE = "{call update_tariff(?,?,?,?,?,?,?)}";
    private static final String SQL_SELECT_BY_ID = "select * from Tariffs where id_tariffs=?";
    private static final String SQL_COUNT_RECORDS = "select count(*) from Tariffs";
    private static final String SQL_DELETE_ID = "delete from Tariffs where id_tariffs=?";

    public TariffDaoImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer countRecords() throws DaoException {
        Integer count = 0;
        Connection connection = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(SQL_COUNT_RECORDS);
            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (SQLException | ConnectionPoolException e) {
            LOGGER.log(Level.ERROR, "can't count tariff records");
            throw new DaoException(e.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }
        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List findLimited(Integer start, Integer end, String lang) throws DaoException {
        List<Tariff> tariffs = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            CallableStatement cs = connection.prepareCall(SQL_SP_SELECT_LIMITED);
            cs.setString(1, lang);
            cs.setInt(2, start);
            cs.setInt(3, end - start);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                tariffs.add(getNewEntity(rs));
            }
            rs.close();
            cs.close();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }
        return tariffs;
    }

    @Override
    public List<Tariff> selectAllByLang(String lang) throws DaoException {
        Connection connection = null;
        List<Tariff> entities = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            CallableStatement cs = connection.prepareCall(SQL_SP_SELECT_ALL);
            cs.setString(1, lang);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                entities.add(getNewEntity(rs));
            }
            rs.close();
            cs.close();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }
        return entities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void executeDelete(Connection connection, Integer id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_DELETE_ID)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResultSet executeSelectAll(Connection connection) throws SQLException {
        CallableStatement cs = connection.prepareCall(SQL_SP_SELECT_ALL);
        cs.setString(1, "en");
        return cs.executeQuery();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResultSet executeSelectById(Connection connection, Integer id) throws SQLException {
        CallableStatement cs = connection.prepareCall(SQL_SP_SELECT_BY_ID);
        cs.setString(1, "en");
        cs.setInt(2, id);
        return cs.executeQuery();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void executeUpdate(Connection connection, Tariff entity) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_SP_UPDATE)) {
            ps.setInt(1, entity.getTariffId());
            ps.setInt(2, entity.getRecievingSpeed());
            ps.setInt(3, entity.getTransferSpeed());
            ps.setInt(4, entity.getPrice());
            ps.setString(5, entity.getLang());
            ps.setString(6, entity.getName());
            ps.setString(7, entity.getDescription());
            ps.executeUpdate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void executeCreate(Connection connection, Tariff entity) throws SQLException {
        try (CallableStatement insertTariffs = connection.prepareCall(SQL_SP_INSERT)) {
            insertTariffs.setInt(1, entity.getRecievingSpeed());
            insertTariffs.setInt(2, entity.getTransferSpeed());
            insertTariffs.setInt(3, entity.getPrice());
            insertTariffs.setString(4, entity.getLang());
            insertTariffs.setString(5, entity.getName());
            insertTariffs.setString(6, entity.getDescription());
            insertTariffs.executeUpdate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Tariff getNewEntity(ResultSet rs) throws SQLException {
        Tariff tariff = new Tariff();
        tariff.setTariffId(rs.getInt("id_tariffs"));
        tariff.setName(rs.getString("name"));
        tariff.setPrice(rs.getInt("price"));
        tariff.setRecievingSpeed(rs.getInt("recieving_speed"));
        tariff.setTransferSpeed(rs.getInt("transfer_speed"));
        tariff.setDescription(rs.getString("description"));
        tariff.setLang(rs.getString("lang"));
        return tariff;
    }

}
