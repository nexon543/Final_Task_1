package com.epam.provider.dao.impl;

import com.epam.provider.dao.DaoException;
import com.epam.provider.dao.GenericDao;
import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.dao.pool.ConnectionPoolException;
import com.epam.provider.model.Entity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class Abstract DAO To implement this class subclasses may implement getSql methods or
 * override getPS methods if they need
 *
 * @author Gleb Aksenov
 */
public abstract class AbstractDao<T extends Entity> implements GenericDao<T> {

  @Override
  public Integer countRecords() throws DaoException {
    Integer count = 0;
    try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(getSqlCount());
        ResultSet rs = ps.executeQuery()) {
      if (rs.next()) {
        count = rs.getInt(1);
      }
    } catch (SQLException | ConnectionPoolException e) {
      throw new DaoException(e.getMessage(), e);
    }
    return count;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<T> findAll(String lang) throws DaoException {
    List<T> entities = new ArrayList<>();
    try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = getPSFindAll(connection, lang);
        ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        T entity = getNewEntity(rs);
        entities.add(entity);
      }
    } catch (SQLException | ConnectionPoolException e) {
      throw new DaoException(e.getMessage(), e);
    }
    return entities;
  }


  @Override
  public List<T> findLimited(Integer start, Integer end, String lang) throws DaoException {
    List<T> result = new ArrayList<>();
    try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = getPSFindLimited(connection, start, end, lang);
        ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        result.add(getNewEntity(rs));
      }
      return result;
    } catch (Exception e) {
      throw new DaoException("can't find entities", e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public T findById(Integer id, String lang) throws DaoException {
    T entity = null;
    try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = getPSFindById(connection, id, lang);
        ResultSet rs = ps.executeQuery()) {
      if (rs.next()) {
        entity = getNewEntity(rs);
      }
      return entity;
    } catch (Exception e) {
      throw new DaoException("can't find entity by id", e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void delete(Integer id) throws DaoException {
    try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(getSqlDelete())) {
      ps.setInt(1, id);
      ps.executeUpdate();
    } catch (Exception e) {
      throw new DaoException("can't delete record with id=" + id, e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void create(T entity) throws DaoException {
    try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(getSqlCreate())) {
      setPSCreate(ps, entity);
      ps.executeUpdate();
    } catch (Exception e) {
      throw new DaoException(e.getMessage(), e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void update(T entity) throws DaoException {
    try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(getSqlUpdate())) {
      setPSUpdate(ps, entity);
      ps.executeUpdate();
    } catch (Exception e) {
      throw new DaoException(e.getMessage(), e);
    }
  }


  PreparedStatement getPSFindAll(Connection con, String lang) throws SQLException {
    PreparedStatement ps = null;
    try {
      ps = con.prepareStatement(getSqlFindAll());
      if (lang != null) {
        ps.setString(1, lang);
      }
      return ps;
    } catch (SQLException e) {
      close(ps);
      throw e;
    }
  }

  PreparedStatement getPSFindById(Connection con, Integer id, String lang) throws SQLException {
    PreparedStatement ps = null;
    try {
      ps = con.prepareStatement(getSqlFindById());
      ps.setInt(1, id);
      if (lang != null) {
        ps.setString(2, lang);
      }
      return ps;
    } catch (SQLException e) {
      close(ps);
      throw e;
    }
  }

  PreparedStatement getPSFindLimited(Connection con, Integer start, Integer end, String lang)
      throws SQLException {
    PreparedStatement ps = null;
    try {
      ps = con.prepareStatement(getSqlFindLimited());
      if (lang != null) {
        ps.setString(1, lang);
      }
      ps.setInt(2, start);
      ps.setInt(3, end);
      return ps;
    } catch (SQLException e) {
      close(ps);
      throw e;
    }
  }

  protected void close(PreparedStatement ps) throws SQLException {
    if (ps != null) {
      ps.close();
    }
  }

  /**
   * Used in create select and find methods
   */
  abstract T getNewEntity(ResultSet rs) throws SQLException;

  abstract void setPSUpdate(PreparedStatement ps, T entity) throws SQLException;

  abstract void setPSCreate(PreparedStatement ps, T entity) throws SQLException;

  abstract String getSqlDelete();

  abstract String getSqlUpdate();

  abstract String getSqlCreate();

  abstract String getSqlCount();

  abstract String getSqlFindById();

  abstract String getSqlFindAll();

  abstract String getSqlFindLimited();
}
