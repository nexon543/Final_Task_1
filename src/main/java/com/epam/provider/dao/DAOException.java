package com.epam.provider.dao;

/**
 * Created by HP on 27.03.2018.
 */
public class DAOException extends Exception{
    public final static String MESS_CLOSE_CONECTION_ERROR="can't close connection";
    public final static String MESS_FINDING_TARIFF_ERROR="can't find tariff";
    public final static String MESS_INSERT_TARIFF_ERROR="can't insert tariff record";
    public DAOException(String message){
        super(message);
    }
}
