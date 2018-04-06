package com.epam.provider.dao.impl;

import com.epam.provider.dao.DaoException;
import com.epam.provider.dao.TariffDao;
import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.model.Tariff;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TariffDaoImpl extends AbstractDao<Integer, Tariff> implements TariffDao {

    private static final Logger LOGGER = Logger.getLogger(TariffDaoImpl.class);

    private static final String SQL_SP_SELECT_ALL = "{call get_tariffs(?)}";
    private static final String SQL_SP_SELECT_LIMITED = "{call get_tariffs_limited(?, ?, ?)}";
    private static final String SQL_SP_INSERT = "{call insert_tariff(?,?,?,?,?,?,?)}";
    private static final String SQL_SP_UPDATE = "{call updatet_tariff(?,?,?,?,?,?,?)}";
    private static final String SQL_SELECT_BY_ID = "select * from Tariffs where id_tariffs=?";
    private static final String SQL_COUNT_RECORDS = "select count(*) from Tariffs";
    private static final String SQL_DELETE_ID = "delete from Tariffs where id_tariffs=?";


    @Override
    public Integer countRecords() throws DaoException {
        Integer count = 0;
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SQL_COUNT_RECORDS);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException(DaoException.MESS_FINDING_TARIFF_ERROR);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }
        return count;
    }

    @Override
    public List findLimited(Integer start, Integer end) throws DaoException {
        List<Tariff> tariffs = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try {
            CallableStatement cs = connection.prepareCall(SQL_SP_SELECT_LIMITED);
            cs.setString(1, "en");
            cs.setInt(2, start);
            cs.setInt(3, end - start);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                tariffs.add(getNewEntity(rs));
            }
        } catch (SQLException e) {
            throw new DaoException(DaoException.MESS_FINDING_TARIFF_ERROR);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }
        return tariffs;
    }

    @Override
    public void update(Tariff entity) throws DaoException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        delete(entity.getIdTarifs());
        create(entity);
    }

    @Override
    protected void executeDelete(Connection connection, Integer id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_DELETE_ID);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
    }

    @Override
    protected ResultSet executeSelectAll(Connection connection) throws SQLException {
        CallableStatement cs = connection.prepareCall(SQL_SP_SELECT_ALL);
        cs.setString(1, "en");
        return cs.executeQuery();
    }

    @Override
    protected ResultSet executeSelectById(Connection connection, Integer id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_SELECT_BY_ID);
        ps.setInt(1, id);
        return ps.executeQuery();
    }

    @Override
    protected void executeUpdate(Connection connection, Tariff entity) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_SP_UPDATE);
        ps.setInt(1, entity.getRecievingSpeed());
        ps.setInt(2, entity.getTransferSpeed());
        ps.setInt(3, entity.getPrice());
        ps.setInt(4, 1);
        ps.setString(5, entity.getLang());
        ps.setString(6, entity.getName());
        ps.setString(7, entity.getDescription());
        ps.executeUpdate();
    }

    @Override
    protected void executeCreate(Connection connection, Tariff entity) throws SQLException {
        CallableStatement insertTariffs = connection.prepareCall(SQL_SP_INSERT);
        insertTariffs.setInt(1, entity.getRecievingSpeed());
        insertTariffs.setInt(2, entity.getTransferSpeed());
        insertTariffs.setInt(3, entity.getPrice());
        insertTariffs.setInt(4, 1);
        insertTariffs.setString(5, entity.getLang());
        insertTariffs.setString(6, entity.getName());
        insertTariffs.setString(7, entity.getDescription());
        insertTariffs.executeUpdate();
    }

    @Override
    protected Tariff getNewEntity(ResultSet rs) throws SQLException {
        Tariff tariff = new Tariff();
        tariff.setIdTarifs(rs.getInt("id_tariffs"));
        tariff.setName(rs.getString("name"));
        tariff.setPrice(rs.getInt("price"));
        tariff.setRecievingSpeed(rs.getInt("recieving_speed"));
        tariff.setTransferSpeed(rs.getInt("transfering_speed"));
        tariff.setDescription(rs.getString("description"));
        tariff.setLang(rs.getString("lang"));
        return tariff;
    }

}
