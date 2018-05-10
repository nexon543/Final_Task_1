package com.epam.provider.dao.pool;

/**
 * @author Gleb Akseonov
 */
public class ConnectionPoolException extends Exception {

  ConnectionPoolException(String message, Exception ex) {
    super(message, ex);
  }
  ConnectionPoolException(String message) {
    super(message);
  }
}
