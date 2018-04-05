package com.epam.provider.dao.impl;

import com.epam.provider.dao.DAOException;
import com.epam.provider.dao.UserDAO;
import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by HP on 04.04.2018.
 */
public class UserDAOImpl implements UserDAO {
    public UserDAOImpl(){}

    public User findProfileByLoginPass(String login, String pass) throws DAOException {
        User user = new User();
        Connection connection= ConnectionPool.getInstance().getConnection();
        try {
        PreparedStatement st = connection.prepareStatement("select * from Users where login=? and pass=?");
        st.setString(1, login);
        st.setString(2, pass);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            user.setIdUsers(rs.getInt("id_users"));
            user.setIdProfiles(rs.getInt("id_profiles"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("pass"));
            user.setRole(rs.getString("role"));
        }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        Connection connection= ConnectionPool.getInstance().getConnection();
        return null;
    }

    @Override
    public User findEntityById(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void delete(User entity) {
    }

    @Override
    public void create(User entity) {

    }

    @Override
    public User update(User entity) {
        return null;
    }
}
