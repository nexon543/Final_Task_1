package com.epam.provider.dao;

import com.epam.provider.model.Entity;

import java.util.List;

public interface CrudDao <K, T extends Entity> {
    List<T> findAll() throws DaoException;

    T findById(K id) throws DaoException;

    void delete(K id) throws DaoException;

    void create(T entity) throws DaoException;

    void update(T entity) throws DaoException;
}
