package com.epam.provider.dao.impl;

import com.epam.provider.dao.DAOException;
import com.epam.provider.dao.ProfileDAO;
import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.model.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by HP on 04.04.2018.
 */
public class ProfileDAOImpl implements ProfileDAO {

    private static final String SQL_SELECT_ALL = "select * from Profiles";
    private static final String SQL_SELECT_BY_ID = "select * from Profiles where id_profiles=?";


    @Override
    public List<Profile> findAll() throws DAOException {
        return null;
    }

    @Override
    public void delete(Integer id) throws DAOException {
    }

    @Override
    public void delete(Profile entity) throws DAOException {
    }

    @Override
    public void create(Profile entity) throws DAOException {
    }

    @Override
    public Profile update(Profile entity) throws DAOException {
        return null;
    }

    @Override
    public Profile findEntityById(Integer id) throws DAOException {
        Profile profile = new Profile();
        Connection connection = ConnectionPool.getInstance().getConnection();
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
            st.close();
        } catch (SQLException e) {
            throw new DAOException("error finding profile by id");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }
        return profile;
    }
}
