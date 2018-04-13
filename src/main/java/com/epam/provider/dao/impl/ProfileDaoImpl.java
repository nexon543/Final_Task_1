package com.epam.provider.dao.impl;

import com.epam.provider.dao.DaoException;
import com.epam.provider.dao.ProfileDao;
import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.dao.pool.ConnectionPoolException;
import com.epam.provider.model.Profile;

import java.sql.*;

/**
 * @author Gleb Akseonov
 */
public class ProfileDaoImpl extends AbstractDao<Profile> implements ProfileDao {

    private static final String SQL_SELECT_ALL = "select * from profiles";
    private static final String SQL_SELECT_BY_ID = "select * from profiles where id_profiles=?";
    private static final String SQL_PS_INSERT = "insert into profiles (`first_name`, `second_name`, `passport`, `id_tariffs`, `balance`, `register_date`, `login`,`pass`,`role`)VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String SQL_PS_UPDATE = "update profiles set `first_name`=?, `second_name`=?, `passport`=?, `id_tariffs`=?, `balance`=?, `register_date`=? , `login`=?,`pass`=?,`role`=? where id_profiles=?";
    private static final String SQL_PS_DELETE_ID = "delete from profiles where id_tariffs=?";

    private static final String SQL_PS_SELECT_LOG_PASS = "select * from profiles where login=? and pass=?";
    private static final String SQL_PS_SELECT_LOG = "select * from profiles where login=?";

    @Override
    public Profile findByLoginPass(String login, String pass) throws DaoException {
        Profile profile = new Profile();
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement st = connection.prepareStatement(SQL_PS_SELECT_LOG_PASS);
            st.setString(1, login);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                profile = getNewEntity(rs);
            }
            rs.close();
            st.close();
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
        return profile;
    }

    @Override
    public Profile finByLogin(String login) throws DaoException {
        Profile profile = new Profile();
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement st = connection.prepareStatement(SQL_PS_SELECT_LOG);
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                profile = getNewEntity(rs);
            }
            rs.close();
            st.close();
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
        return profile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void executeDelete(Connection connection, Integer id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_PS_DELETE_ID)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResultSet executeSelectAll(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        return st.executeQuery(SQL_SELECT_ALL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResultSet executeSelectById(Connection connection, Integer id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_SELECT_BY_ID);
        ps.setInt(1, id);
        return ps.executeQuery();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void executeUpdate(Connection connection, Profile entity) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_PS_UPDATE)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getSecondName());
            ps.setString(3, entity.getPassport());
            ps.setInt(4, entity.getIdTariffs());
            ps.setDouble(5, entity.getBalance());
            ps.setDate(6, entity.getRegisterDate());
            ps.setString(7, entity.getLogin());
            ps.setString(8, entity.getPassword());
            ps.setString(9, entity.getRole());
            ps.setInt(10, entity.getProfileId());
            ps.executeUpdate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void executeCreate(Connection connection, Profile entity) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(SQL_PS_INSERT)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getSecondName());
            ps.setString(3, entity.getPassport());
            ps.setInt(4, entity.getIdTariffs());
            ps.setDouble(5, entity.getBalance());
            ps.setDate(6, entity.getRegisterDate());
            ps.setString(7, entity.getLogin());
            ps.setString(8, entity.getPassword());
            ps.setString(9, entity.getRole());
            ps.executeUpdate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Profile getNewEntity(ResultSet rs) throws SQLException {
        Profile profile = new Profile();
        profile.setFirstName(rs.getString("first_name"));
        profile.setBalance(rs.getDouble("balance"));
        profile.setPassport(rs.getString("passport"));
        profile.setRegisterDate(rs.getDate("register_date"));
        profile.setSecondName(rs.getString("second_name"));
        profile.setIdTariffs(rs.getInt("id_tariffs"));
        profile.setProfileId(rs.getInt("id_profiles"));
        profile.setLogin(rs.getString("login"));
        profile.setPassword(rs.getString("pass"));
        profile.setRole(rs.getString("role"));

        return profile;
    }

}
