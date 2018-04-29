package com.epam.provider.dao.impl;

import com.epam.provider.model.Payment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Gleb Akseonov
 */
public class PaymentDaoImpl extends AbstractDao<Payment> {

  private static final String SQL_SP_CREATE = "INSERT INTO `transactions`" +
      "(`amount`," +
      "`date`," +
      "`id_profiles`)" +
      "VALUES (?,?,?)";
  private static final String SQL_SP_SELECT_BY_ID = "select * fron transactions where id_transactions=?";

  public PaymentDaoImpl() {
  }


  /**
   * {@inheritDoc}
   */
  @Override
  protected Payment getNewEntity(ResultSet rs) throws SQLException {
    Payment t = new Payment()
        .setAmount(rs.getInt("amount"))
        .setDate((rs.getDate("date")))
        .setTransactionId(rs.getInt("id_transactions"))
        .setIdProfiles(rs.getInt("id_profile"));
    return t;
  }

  @Override
  void setPSUpdate(PreparedStatement ps, Payment entity) throws SQLException {

  }

  @Override
  void setPSCreate(PreparedStatement ps, Payment entity) throws SQLException {
    ps.setInt(1, entity.getAmount());
    ps.setDate(2, entity.getDate());
    ps.setInt(3, entity.getIdProfiles());
  }

  @Override
  String getSqlDelete() {
    throw new UnsupportedOperationException();
  }

  @Override
  String getSqlUpdate() {
    throw new UnsupportedOperationException();
  }

  @Override
  String getSqlCreate() {
    return SQL_SP_CREATE;
  }

  @Override
  String getSqlCount() {
    throw new UnsupportedOperationException();
  }

  @Override
  String getSqlFindById() {
    throw new UnsupportedOperationException();
  }

  @Override
  String getSqlFindAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  String getSqlFindLimited() {
    throw new UnsupportedOperationException();
  }
}