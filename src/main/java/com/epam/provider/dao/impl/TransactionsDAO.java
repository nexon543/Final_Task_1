package com.epam.provider.dao.impl;

import com.epam.provider.dao.AbstractDAO;
import com.epam.provider.model.Transaction;

import java.util.List;

/**
 * Created by HP on 26.03.2018.
 */
public class TransactionsDAO extends AbstractDAO<Integer, Transaction> {

    public TransactionsDAO() {
    }

    @Override
    public List<Transaction> findAll() {
        return null;
    }

    @Override
    public Transaction findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean delete(Transaction entity) {
        return false;
    }

    @Override
    public boolean create(Transaction entity) {
        return false;
    }

    @Override
    public Transaction update(Transaction entity) {
        return null;
    }
}
