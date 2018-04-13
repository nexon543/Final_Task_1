/**
 *
 */
package com.epam.provider.dao;

/**
 * This is an exception for DAO layer
 *
 * @author Gleb Aksenov
 */
public class DaoException extends Exception {
    public final static String MESS_CLOSE_CONECTION_ERROR = "can't close connection";
    public final static String MESS_FINDING_TARIFF_ERROR = "can't find tariff";
    public final static String MESS_INSERT_TARIFF_ERROR = "can't createTariff tariff record";

    public DaoException(String message) {
        super(message);
    }
}
