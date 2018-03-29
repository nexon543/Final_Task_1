package com.epam.provider.dao.impl;

import com.epam.provider.dao.AbstractDAO;
import com.epam.provider.model.Profile;
import com.epam.provider.model.User;

import java.sql.*;
import java.util.List;

/**
 * Created by HP on 28.03.2018.
 */
public class UsersDAO extends AbstractDAO<Integer, User> {
    public UsersDAO(Connection connection) {
        super(connection);
    }

    public User findProfileByLoginPass(String login, String pass) throws SQLException {
        User user=new User();
        PreparedStatement st=connection.prepareStatement("select * from Users where login=? and pass=?");
        st.setString(1, login);
        st.setString(2, pass);
        ResultSet rs=st.executeQuery();
        if (rs.next()) {
            user.setIdUsers(rs.getInt("id_users"));
            user.setIdProfiles(rs.getInt("id_profiles"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("pass"));
            user.setRole(rs.getString("role"));
        }
        return user;
    }
    @Override
    public List<User> findAll() throws SQLException {
        return null;
    }

    @Override
    public User findEntityById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public boolean create(User entity) {
        return false;
    }

    @Override
    public User update(User entity) {
        return null;
    }
}
