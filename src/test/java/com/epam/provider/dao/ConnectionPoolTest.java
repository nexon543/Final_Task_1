package com.epam.provider.dao;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.fail;

import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.dao.pool.ConnectionPoolException;
import java.time.Duration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ConnectionPoolTest {

  @BeforeAll
  public static void setUp() {
    try {
      ConnectionPool.initialize();
    } catch (ConnectionPoolException e) {
      fail(e.getMessage());
    }
  }

  @AfterAll
  static void tearDownAll() {
    try {
      ConnectionPool.getInstance().clearConnectionQueue();
    } catch (ConnectionPoolException e) {
      fail(e.getMessage());
    }
  }

  @Test()
  void getConnectionTest() {
    assertTimeout(Duration.ofMillis(1), () -> {
      ConnectionPool.getInstance().getConnection();
    }, "can't get connection");
  }
}
