package com.epam.provider.dao.impl;

import com.epam.provider.dao.ProfileDao;
import com.epam.provider.model.Profile;

import java.sql.*;

public class ProfileDaoImpl extends AbstractDao<Integer, Profile> implements ProfileDao {

    private static final String SQL_SELECT_ALL = "select * from profiles";
    private static final String SQL_SELECT_BY_ID = "select * from profiles where id_profiles=?";
    private static final String SQL_PS_INSERT = "insert into profiles (`first_name`, `second_name`, `passport`, `id_tariffs`, `balance`, `register_date`)VALUES(?,?,?,?,?,?)";
    private static final String SQL_PS_UPDATE = "update profiles set (`first_name`=?, `second_name`=?, `passport`=?, `id_tariffs`=?, `balance`=?, `register_date`=? where id_profiles=?";
    private static final String SQL_PS_DELETE_ID = "delete from profiles where id_tariffs=?";

    @Override
    protected void executeDelete(Connection connection, Integer id) throws SQLException {
        PreparedStatement ps=connection.prepareStatement(SQL_PS_DELETE_ID);
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    @Override
    protected ResultSet executeSelectAll(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        return st.executeQuery(SQL_SELECT_ALL);
    }

    @Override
    protected ResultSet executeSelectById(Connection connection, Integer id) throws SQLException {
        PreparedStatement ps=connection.prepareStatement(SQL_SELECT_BY_ID);
        ps.setInt(1, id);
        return ps.executeQuery();
    }


    @Override
    protected void executeUpdate(Connection connection, Profile entity) throws SQLException {
        PreparedStatement ps=connection.prepareStatement(SQL_PS_UPDATE);
        ps.setString(1, entity.getFirstName());
        ps.setString(2, entity.getSecondName());
        ps.setString(3, entity.getPassport());
        ps.setInt(4, entity.getTariff());
        ps.setDouble(5, entity.getBalance());
        ps.setDate(6,entity.getRegisterDate());
        ps.setInt(7, entity.getIdProfiles());
        ps.executeUpdate();
    }

    @Override
    protected void executeCreate(Connection connection, Profile entity) throws SQLException {
        PreparedStatement ps=connection.prepareStatement(SQL_PS_INSERT);
        ps.setString(1, entity.getFirstName());
        ps.setString(2, entity.getSecondName());
        ps.setString(3, entity.getPassport());
        ps.setInt(4, entity.getTariff());
        ps.setDouble(5, entity.getBalance());
        ps.setDate(6,entity.getRegisterDate());
        ps.executeUpdate();
    }

    @Override
    protected Profile getNewEntity(ResultSet rs) throws SQLException {
        Profile profile=new Profile();
        profile.setFirstName(rs.getString("first_name"));
        profile.setBalance(rs.getDouble("balance"));
        profile.setPassport(rs.getString("passport"));
        profile.setRegisterDate(rs.getDate("register_date"));
        profile.setSecondName(rs.getString("second_name"));
        profile.setTariff(rs.getInt("id_tariffs"));
        return profile;
    }

}
