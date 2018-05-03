package com.epam.provider.dao;

import static org.junit.jupiter.api.Assertions.fail;

import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.dao.pool.ConnectionPoolException;
import com.epam.provider.service.ServiceException;
import com.epam.provider.service.TariffService;
import com.epam.provider.service.impl.TariffServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TariffServiceImpTest {

  @BeforeAll
  public static void setUp() {
    try {
      ConnectionPool.initialize();
    } catch (ConnectionPoolException e) {
      fail(e.getMessage());
    }
  }

  @Test
  void getNumberOfRecords() {
    TariffService tariffService = new TariffServiceImpl();

    try {
      tariffService.getNumberOfRecords();
    } catch (ServiceException e) {
      fail(e.getMessage());
    }
  }
}
