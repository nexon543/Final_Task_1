package com.epam.provider.dao;

import com.epam.provider.model.Entity;

import java.util.List;

public interface CrudDao <K, T extends Entity> {
    List<T> findAll() throws DAOException;

    T findById(K id) throws DAOException;

    void delete(K id) throws DAOException;

    void create(T entity) throws DAOException;

    void update(T entity) throws DAOException;
}
