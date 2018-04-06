package com.epam.provider.dao;

import com.epam.provider.model.Tariff;

import java.util.List;


public interface TariffDao extends CrudDao<Integer, Tariff> {

    Integer countRecords() throws DaoException;

    List findLimited(Integer start, Integer end) throws DaoException;

}