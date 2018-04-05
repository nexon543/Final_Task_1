package com.epam.provider.dao;

import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.model.Entity;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by HP on 26.03.2018.
 */
public interface AbstractDAO<K, T extends Entity> {

    List<T> findAll() throws DAOException;

    T findEntityById(K id) throws DAOException;

    void delete(K id) throws DAOException;

    void delete(T entity) throws DAOException;

    void create(T entity) throws DAOException;

    T update(T entity) throws DAOException;

}
