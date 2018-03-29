package com.epam.provider.dao.impl;

import com.epam.provider.dao.AbstractDAO;
import com.epam.provider.model.Profile;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by HP on 26.03.2018.
 */
public class ProfilesDAO extends AbstractDAO<Integer, Profile> {
    public ProfilesDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Profile> findAll() throws SQLException {
        return null;
    }

    @Override
    public Profile findEntityById(Integer id) throws SQLException {
        Statement st=connection.createStatement();
        ResultSet rs=st.executeQuery("select * from Profiles where id_profiles="+id);
        Profile profile = new Profile();
        if(rs.next()) {
            profile.setFirstName(rs.getString("first_name"));
            profile.setBalance(rs.getDouble("balance"));
            profile.setPassport(rs.getString("passport"));
            profile.setRegisterDate(rs.getDate("register_date"));
            profile.setSecondName(rs.getString("second_name"));
            profile.setTariff(rs.getInt("id_tariffs"));
        }
        rs.close();
        close(st);
        return profile;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Profile entity) {
        return false;
    }

    @Override
    public boolean create(Profile entity) {
        return false;
    }

    @Override
    public Profile update(Profile entity) {
        return null;
    }
}
