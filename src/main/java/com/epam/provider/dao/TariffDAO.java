package com.epam.provider.dao;

import com.epam.provider.model.Entity;
import com.epam.provider.model.Tariff;

import java.util.List;

/**
 * Created by HP on 30.03.2018.
 */
public interface TariffDao extends CrudDao<Integer, Tariff>{

    Integer countRecords() throws DAOException;

    List findLimited(Integer start, Integer end) throws DAOException;

}
