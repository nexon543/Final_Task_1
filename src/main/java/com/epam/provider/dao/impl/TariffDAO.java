package com.epam.provider.dao.impl;

import com.epam.provider.dao.AbstractDAO;
import com.epam.provider.dao.DAOException;
import com.epam.provider.model.Tariff;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 30.03.2018.
 */
public class TariffDAO extends AbstractDAO<Integer, Tariff> {

    private static Logger logger = Logger.getLogger(TariffDAO.class);

    private static final String SQL_SP_GET_TARIFFS="{call get_tariffs(?)}";
    private static final String SQL_SP_GET_TARIFFS_LIMITED="{call get_tariffs_limited(?, ?, ?)}";
    private static final String SQL_SP_INSERT_TARIFFS="{call insert_tariff(?,?,?,?,?,?,?)}";
    private static final String SQL_INSERT_TEXT_TARIFFS="INSERT INTO ttariffs (`lang`,`id_tariffs`,`name`,`description`)VALUES(?,?,?,?)";
    private static final String SQL_SELECT_ALL = "select * from Tariffs";
    private static final String SQL_LIMITED_SELECT = "select * from Tariffs ORDER BY id_tariffs LIMIT ?,?";
    private static final String SQL_SELECT_BY_ID = "select * from Tariffs where id_tariffs=?";
    private static final String SQL_COUNT_RECORDS="select count(*) from Tariffs";

    public TariffDAO() {
    }

    public Integer countRecords() throws DAOException {
        Integer count=0;
        try {
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(SQL_COUNT_RECORDS);
        if(rs.next()){
            count=rs.getInt(1);
        }
        } catch (SQLException e) {
            throw new DAOException(DAOException.MESS_FINDING_TARIFF_ERROR);
        }
        return count;
    }

    @Override
    public List findAll() throws DAOException {
        List<Tariff> tariffs = new ArrayList<>();
        Statement st = null;
        CallableStatement cs;

        try {
            cs =connection.prepareCall(SQL_SP_GET_TARIFFS);
            cs.setString(1, "en");
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(SQL_SELECT_ALL);
            while (rs.next()) {
                tariffs.add(getTariffObj(rs));
            }
        } catch (SQLException e) {
            throw new DAOException(DAOException.MESS_FINDING_TARIFF_ERROR);
        }
        return tariffs;
    }

    public List findLimited(Integer start, Integer end) throws DAOException {
        List<Tariff> tariffs = new ArrayList<>();
        try {
            CallableStatement cs = connection.prepareCall(SQL_SP_GET_TARIFFS_LIMITED);
            cs.setString(1, "en");
            cs.setInt(2, start);
            cs.setInt(3,end-start);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                tariffs.add(getTariffObj(rs));
            }
        } catch (SQLException e) {
            throw new DAOException(DAOException.MESS_FINDING_TARIFF_ERROR);
        }
        return tariffs;
    }

    @Override
    public Tariff findEntityById(Integer id) throws DAOException {
        Tariff tariff=null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_SELECT_BY_ID);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tariff=getTariffObj(rs);
            }
        } catch (SQLException e) {
            throw new DAOException(DAOException.MESS_FINDING_TARIFF_ERROR);
        }
        return tariff;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Tariff entity) {
        return false;
    }

    @Override
    public boolean create(Tariff entity) throws DAOException {
        try {
            CallableStatement insertTariffs=connection.prepareCall(SQL_SP_INSERT_TARIFFS);
            insertTariffs.setInt(1, entity.getRecievingSpeed());
            insertTariffs.setInt(2, entity.getTransferSpeed());
            insertTariffs.setInt(3, entity.getPrice());
            insertTariffs.setInt(4, 1);
            insertTariffs.setString(5, entity.getLang());
            insertTariffs.setString(6, entity.getName());
            insertTariffs.setString(7, entity.getDescription());
            insertTariffs.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Tariff update(Tariff entity) {
        return null;
    }

    private Tariff getTariffObj(ResultSet rs) throws SQLException {
        Tariff tariff = new Tariff();
        tariff.setIdTarifs(rs.getInt("id_tariffs"));
        tariff.setName(rs.getString("name"));
        tariff.setPrice(rs.getInt("price"));
        tariff.setRecievingSpeed(rs.getInt("recieving_speed"));
        tariff.setTransferSpeed(rs.getInt("transfering_speed"));
        tariff.setDescription(rs.getString("description"));
        return tariff;
    }
}
