package com.epam.provider.dao.impl;

import com.epam.provider.dao.DAOException;
import com.epam.provider.dao.TransactionDAO;
import com.epam.provider.model.Transaction;

import java.util.List;

/**
 * Created by HP on 04.04.2018.
 */
public class TransactionDAOImpl implements TransactionDAO {

    @Override
    public List<Transaction> findAll() throws DAOException {
        return null;
    }

    @Override
    public Transaction findEntityById(Integer id) throws DAOException {
        return null;
    }

    @Override
    public void delete(Integer id) throws DAOException {

    }

    @Override
    public void delete(Transaction entity) throws DAOException {

    }

    @Override
    public void create(Transaction entity) throws DAOException {

    }

    @Override
    public Transaction update(Transaction entity) throws DAOException {
       return null;
    }
}
