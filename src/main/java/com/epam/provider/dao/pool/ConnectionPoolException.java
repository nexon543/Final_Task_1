package com.epam.provider.dao.pool;


public class ConnectionPoolException extends Exception {
    public ConnectionPoolException(String message, Exception ex) {
        super(message, ex);
    }

    public ConnectionPoolException(String message) {
        super(message);
    }
}
