package com.epam.provider.dao.impl;

import com.epam.provider.model.Tariff;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import static com.epam.provider.dao.DBTableFieldName.*;

/**
 * @author Gleb Akseonov
 */
public class TariffDaoImpl extends AbstractDao<Tariff> {

  private static final String SQL_SP_SELECT_ALL = "{call get_tariffs(?)}";
  private static final String SQL_SP_SELECT_BY_ID = "{call get_tariffs_by_id(?,?)}";
  private static final String SQL_SP_SELECT_LIMITED = "{call get_tariffs_limited(?, ?, ?)}";
  private static final String SQL_SP_INSERT = "{call insert_tariff(?,?,?,?,?,?)}";
  private static final String SQL_SP_UPDATE = "{call update_tariff(?,?,?,?,?,?,?)}";
  private static final String SQL_COUNT_RECORDS = "select count(*) from Tariffs";
  private static final String SQL_DELETE_ID = "{call delete_tariff(?)}";

  public TariffDaoImpl() {
  }

  @Override
  String getSqlDelete() {
    return SQL_DELETE_ID;
  }

  @Override
  String getSqlUpdate() {
    return SQL_SP_UPDATE;
  }

  @Override
  String getSqlCreate() {
    return SQL_SP_INSERT;
  }

  @Override
  String getSqlCount() {
    return SQL_COUNT_RECORDS;
  }

  @Override
  String getSqlFindById() {
    return SQL_SP_SELECT_BY_ID;
  }

  @Override
  String getSqlFindAll() {
    return SQL_SP_SELECT_ALL;
  }

  @Override
  String getSqlFindLimited() {
    return SQL_SP_SELECT_LIMITED;
  }

  @Override
  void setPSUpdate(PreparedStatement ps, Tariff entity) throws SQLException {
    ps.setInt(1, entity.getTariffId());
    ps.setInt(2, entity.getReceivingSpeed());
    ps.setInt(3, entity.getTransferSpeed());
    ps.setInt(4, entity.getPrice());
    ps.setString(5, entity.getLang());
    ps.setString(6, entity.getName());
    ps.setString(7, entity.getDescription());
  }

  @Override
  void setPSCreate(PreparedStatement insertTariffs, Tariff entity) throws SQLException {
    insertTariffs.setInt(1, entity.getReceivingSpeed());
    insertTariffs.setInt(2, entity.getTransferSpeed());
    insertTariffs.setInt(3, entity.getPrice());
    insertTariffs.setString(4, entity.getLang());
    insertTariffs.setString(5, entity.getName());
    insertTariffs.setString(6, entity.getDescription());
  }


  /**
   * {@inheritDoc}
   */
  @Override
  protected Tariff getNewEntity(ResultSet rs) throws SQLException {
    Tariff tariff = new Tariff();
    tariff.setTariffId(rs.getInt(TARIFF_ID));
    tariff.setName(rs.getString(TARIFF_NAME));
    tariff.setPrice(rs.getInt(TARIFF_PRICE));
    tariff.setReceivingSpeed(rs.getInt(TARIFF_RECIEVE_SPEED));
    tariff.setTransferSpeed(rs.getInt(TARIFF_TRANSFER_SPEED));
    tariff.setDescription(rs.getString(TARIFF_DESCRIPTION));
    tariff.setLang(rs.getString(TARIFF_LANG));
    return tariff;
  }

}
