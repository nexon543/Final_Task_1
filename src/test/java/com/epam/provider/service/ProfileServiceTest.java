package com.epam.provider.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.dao.pool.ConnectionPoolException;
import com.epam.provider.model.Profile;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProfileServiceTest {

  private static Profile profile;
  private static ProfileService profileService=ServiceFactory.getProfileService();

  @BeforeAll
  public static void setUp() {
    try {
      ConnectionPool.initialize();
    } catch (Exception e) {
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

  @Test
  public void addBalance() throws ServiceException {
    profile=profileService.findUser("admin");
    Double incrementBalance=5D;
    profileService.addBalance(incrementBalance, profile.getProfileId());
    Double newBalance=profileService.findUser("admin").getBalance();
    Assertions.assertEquals(newBalance, profile.getBalance()+incrementBalance, 0.1);
    incrementBalance=-1D;
    profileService.addBalance(incrementBalance,profile.getProfileId());
    Assertions.assertNotEquals(newBalance, profile.getBalance()+incrementBalance);
  }
}
