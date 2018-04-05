package com.epam.provider.dao;

import com.epam.provider.model.Tariff;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 30.03.2018.
 */
public interface TariffDAO extends AbstractDAO<Integer, Tariff> {

    Integer countRecords() throws DAOException;

    List findLimited(Integer start, Integer end) throws DAOException;

}
