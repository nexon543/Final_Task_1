package com.epam.provider.dao.impl;

import com.epam.provider.dao.DaoException;
import com.epam.provider.dao.UserDao;
import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
    public UserDaoImpl(){}

    public User findByLoginPass(String login, String pass) throws DaoException {
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
            throw new DaoException(e.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DaoException(e.getMessage());
            }
        }
        return user;
    }

    @Override
    protected void executeDelete(Connection connection, Integer id) throws SQLException {

    }

    @Override
    protected ResultSet executeSelectAll(Connection connection) throws SQLException {
        return null;
    }

    @Override
    protected ResultSet executeSelectById(Connection connection, Integer id) throws SQLException {
        return null;
    }

    @Override
    protected void executeUpdate(Connection connection, User entity) throws SQLException {

    }

    @Override
    protected void executeCreate(Connection connection, User entity) throws SQLException {

    }

    @Override
    protected User getNewEntity(ResultSet rs) throws SQLException {
        return null;
    }
}