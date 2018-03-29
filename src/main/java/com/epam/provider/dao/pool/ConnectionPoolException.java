package com.epam.provider.dao.pool;

/**
 * Created by HP on 29.03.2018.
 */
public class ConnectionPoolException extends Exception {
    public ConnectionPoolException(String message, Exception ex){
        super(message, ex);
    }
}
