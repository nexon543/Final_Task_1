package com.epam.provider.dao;

import com.epam.provider.model.Entity;

import java.util.List;

/**
 * Interface CRUD DAO
 *
 * @author Gleb Akseonov
 */
public interface CrudDao<T extends Entity> {

    /**
     * Method selects a list of entities
     *
     * @return list of all entities
     * @throws DaoException
     */
    List<T> findAll() throws DaoException;

    /**
     * Method selects a list of answers with a specific id
     *
     * @param id id to find single entity
     * @return entity by id
     * @throws DaoException
     */
    T findById(Integer id) throws DaoException;

    /**
     * Method deletes entity with a specific id
     *
     * @param id id to delete single entity
     * @throws DaoException
     */
    void delete(Integer id) throws DaoException;

    /**
     * Method persists an entity in database
     *
     * @param entity object to persist
     * @throws DaoException
     */
    void create(T entity) throws DaoException;

    /**
     * Method persists an entity which already exists in database
     *
     * @param entity object to persist
     * @throws DaoException
     */
    void update(T entity) throws DaoException;
}
