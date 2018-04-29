package com.epam.provider.dao.impl;

import com.epam.provider.dao.DaoException;
import com.epam.provider.dao.ProfileDao;
import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.dao.pool.ConnectionPoolException;
import com.epam.provider.model.Profile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Gleb Akseonov
 */
public class ProfileDaoImpl extends AbstractDao<Profile> implements ProfileDao {

  private static final String SQL_SELECT_ALL = "select * from profiles";
  private static final String SQL_SELECT_BY_ID = "select * from profiles where id_profiles=?";
  private static final String SQL_PS_INSERT = "insert into profiles (`first_name`, `second_name`, `passport`, `id_tariffs`, `balance`, `register_date`, `login`,`pass`,`role`)VALUES(?,?,?,?,?,?,?,MD5(?),?)";
  private static final String SQL_PS_UPDATE = "update profiles set `first_name`=?, `second_name`=?, `passport`=?, `id_tariffs`=?, `balance`=?, `register_date`=? , `login`=?,`pass`=MD5(?),`role`=? where id_profiles=?";
  private static final String SQL_PS_UPDATE_PROFILES_TARIFF = "update profiles set `id_tariffs`=? where id_profiles=?";
  private static final String SQL_PS_DELETE_ID = "delete from profiles where id_profiles=?";

  private static final String SQL_PS_SELECT_LOG_PASS = "select * from profiles where login=? and pass=MD5(?)";
  private static final String SQL_PS_SELECT_LOG = "select * from profiles where login=?";

  public ProfileDaoImpl() {
  }

  @Override
  public Profile findByLoginPass(String login, String pass) throws DaoException {
    Profile profile = new Profile();
    try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = getPSFindByLoginPass(connection, login, pass);
        ResultSet rs = ps.executeQuery()) {
      if (rs.next()) {
        profile = getNewEntity(rs);
      }
    } catch (SQLException | ConnectionPoolException e) {
      throw new DaoException(e.getMessage(), e);
    }
    return profile;
  }

  private PreparedStatement getPSFindByLoginPass(Connection con, String login, String pass)
      throws SQLException {
    PreparedStatement ps = null;
    try {
      ps = con.prepareStatement(SQL_PS_SELECT_LOG_PASS);
      ps.setString(1, login);
      ps.setString(2, pass);
      return ps;
    } catch (SQLException e) {
      close(ps);
      throw e;
    }
  }

  @Override
  public Profile finByLogin(String login) throws DaoException {
    Profile profile = new Profile();
    try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = getPSFindByLoginPass(connection, login);
        ResultSet rs = ps.executeQuery();) {
      if (rs.next()) {
        profile = getNewEntity(rs);
      }
    } catch (SQLException | ConnectionPoolException e) {
      throw new DaoException(e.getMessage(), e);
    }
    return profile;
  }

  private PreparedStatement getPSFindByLoginPass(Connection con, String login) throws SQLException {
    PreparedStatement ps = null;
    try {
      ps = con.prepareStatement(SQL_PS_SELECT_LOG);
      ps.setString(1, login);
      return ps;
    } catch (SQLException e) {
      close(ps);
      throw e;
    }
  }

  @Override
  public void updateTariff(Integer idProfile, Integer newIdTariff) throws DaoException {
    try (
        Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement st = connection.prepareStatement(SQL_PS_UPDATE_PROFILES_TARIFF);
    ) {
      st.setInt(1, newIdTariff);
      st.setInt(2, idProfile);
      st.executeUpdate();
    } catch (SQLException | ConnectionPoolException e) {
      throw new DaoException(e.getMessage(), e);
    }
  }


  @Override
  String getSqlDelete() {
    return SQL_PS_DELETE_ID;
  }

  @Override
  String getSqlUpdate() {
    return SQL_PS_UPDATE;
  }

  @Override
  String getSqlCreate() {
    return SQL_PS_INSERT;
  }

  @Override
  String getSqlCount() {
    throw new UnsupportedOperationException();
  }

  @Override
  String getSqlFindById() {
    return SQL_SELECT_BY_ID;
  }

  @Override
  String getSqlFindAll() {
    return SQL_SELECT_ALL;
  }

  @Override
  String getSqlFindLimited() {
    return null;
  }

  @Override
  PreparedStatement getPSFindAll(Connection connection, String lang) throws SQLException {
    PreparedStatement ps = null;
    try {
      ps = connection.prepareStatement(SQL_SELECT_ALL);
      return ps;
    } catch (SQLException e) {
      close(ps);
      throw e;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void setPSUpdate(PreparedStatement ps, Profile entity) throws SQLException {
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
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void setPSCreate(PreparedStatement ps, Profile entity) throws SQLException {
    ps.setString(1, entity.getFirstName());
    ps.setString(2, entity.getSecondName());
    ps.setString(3, entity.getPassport());
    ps.setInt(4, entity.getIdTariffs());
    ps.setDouble(5, entity.getBalance());
    ps.setDate(6, entity.getRegisterDate());
    ps.setString(7, entity.getLogin());
    ps.setString(8, entity.getPassword());
    ps.setString(9, entity.getRole());
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


  @Override
  public Integer countRecords() throws DaoException {
    throw new UnsupportedOperationException();
  }

  @Override
  public List findLimited(Integer start, Integer end, String lang) throws DaoException {
    throw new UnsupportedOperationException();
  }
}
