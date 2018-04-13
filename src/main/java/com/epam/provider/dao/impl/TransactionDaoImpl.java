package com.epam.provider.dao.impl;

import com.epam.provider.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Gleb Akseonov
 */
public class TransactionDaoImpl extends AbstractDao<Transaction> {
    private static final String SQL_SP_CREATE = "INSERT INTO `transactions`" +
            "(`amount`," +
            "`date`," +
            "`id_profiles`)" +
            "VALUES (?,?,?)";
    private static final String SQL_SP_SELECT_BY_ID = "select * fron transactions where id_transactions=?";

    public TransactionDaoImpl() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void executeDelete(Connection connection, Integer id) throws SQLException {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResultSet executeSelectAll(Connection connection) throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ResultSet executeSelectById(Connection connection, Integer id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_SP_SELECT_BY_ID);
        ps.setInt(1, id);
        return ps.executeQuery();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void executeUpdate(Connection connection, Transaction entity) throws SQLException {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void executeCreate(Connection connection, Transaction entity) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_SP_CREATE);
        ps.setInt(1, entity.getAmount());
        ps.setDate(2, entity.getDate());
        ps.setInt(3, entity.getIdProfiles());
        ps.executeUpdate();
        ps.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Transaction getNewEntity(ResultSet rs) throws SQLException {

        Transaction t = new Transaction()
                .setAmount(rs.getInt("amount"))
                .setDate((rs.getDate("date")))
                .setTransactionId(rs.getInt("id_transactions"))
                .setIdProfiles(rs.getInt("id_profile"));
        return t;
    }
}
