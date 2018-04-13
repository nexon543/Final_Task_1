package com.epam.provider.service;

import com.epam.provider.model.Tariff;

import java.util.List;

/**
 * This class serves to process operations with tariffs
 *
 * @author Gleb Aksenov
 */
public interface TariffService {
    /**
     * Method sves tariff object to the database of throws exception
     *
     * @param tariff object to save
     * @throws ServiceException
     */
    void createTariff(Tariff tariff) throws ServiceException;

    void updateTariff(Tariff tariff) throws ServiceException;


    /**
     * Method counts all tariffs in the database
     *
     * @return number of founded tariffs
     * @throws ServiceException
     */
    Integer getNumberOfRecords() throws ServiceException;

    /**
     * Method selects sublist of tariffs limited from start to end ordered by id
     *
     * @param start index to open sublist
     * @param end   index to close sublist
     * @return number of founded tariffs
     * @throws ServiceException
     */
    List<Tariff> getTariffs(Integer start, Integer end, String lang) throws ServiceException;

    /**
     * Method selects a list of tariffs
     *
     * @return list of all tariffs
     * @throws ServiceException
     */
    List<Tariff> getAllTariffs(String lang) throws ServiceException;

    /**
     * Method selects tariff for a specific client
     *
     * @return tariff object
     * @throws ServiceException
     */
    Tariff getTariffById(Integer id) throws ServiceException;
}
