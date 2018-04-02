package com.epam.provider.dao.impl;

import com.epam.provider.dao.AbstractDAO;
import com.epam.provider.dao.DAOException;
import com.epam.provider.model.Profile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by HP on 26.03.2018.
 */
public class ProfilesDAO extends AbstractDAO<Integer, Profile> {

    private static final String SQL_SELECT_ALL = "select * from Profiles";
    private static final String SQL_SELECT_BY_ID = "select * from Profiles where id_profiles=?";

    public ProfilesDAO() {
    }

    @Override
    public List<Profile> findAll() throws SQLException {
        return null;
    }

    @Override
    public Profile findEntityById(Integer id) throws DAOException {
        Profile profile = new Profile();
        try {
            PreparedStatement st = connection.prepareStatement(SQL_SELECT_BY_ID);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                profile.setFirstName(rs.getString("first_name"));
                profile.setBalance(rs.getDouble("balance"));
                profile.setPassport(rs.getString("passport"));
                profile.setRegisterDate(rs.getDate("register_date"));
                profile.setSecondName(rs.getString("second_name"));
                profile.setTariff(rs.getInt("id_tariffs"));
            }
            rs.close();
            close(st);
        } catch (SQLException e) {
            throw new DAOException("error finding profile by id");
        }
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
