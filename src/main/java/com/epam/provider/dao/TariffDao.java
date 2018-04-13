package com.epam.provider.dao;

import com.epam.provider.model.Tariff;

import java.util.List;

/**
 * Interface Tariff Dao
 *
 * @author Gleb Akseonov
 */
public interface TariffDao extends CrudDao<Tariff> {

    /**
     * Method counts all entities of a specific type in the database
     *
     * @return number of founded entities
     * @throws DaoException
     */
    Integer countRecords() throws DaoException;

    /**
     * Method selects sublist of entities limited from start to end ordered by id
     *
     * @param start index to open sublist
     * @param end   index to close sublist
     * @return number of founded entities
     * @throws DaoException
     */
    List findLimited(Integer start, Integer end, String lang) throws DaoException;

    List<Tariff> selectAllByLang(String lang) throws DaoException;

}
