package com.epam.provider.dao.impl;

import com.epam.provider.dao.TransactionDao;
import com.epam.provider.model.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TransactionDaoImpl extends AbstractDao<Integer, Transaction> implements TransactionDao {

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
    protected void executeUpdate(Connection connection, Transaction entity) throws SQLException {

    }

    @Override
    protected void executeCreate(Connection connection, Transaction entity) throws SQLException {

    }

    @Override
    protected Transaction getNewEntity(ResultSet rs) throws SQLException {
        return null;
    }
}
