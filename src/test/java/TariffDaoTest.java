import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.epam.provider.dao.DaoException;
import com.epam.provider.dao.GenericDao;
import com.epam.provider.dao.factory.DaoFactory;
import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.dao.pool.ConnectionPoolException;
import com.epam.provider.model.Tariff;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class TariffDaoTest {

  static GenericDao<Tariff> tariffDao;
  static Tariff tariff;

  @BeforeAll
  public static void setUp() {
    try {
      ConnectionPool.initialize();
    } catch (ConnectionPoolException e) {
      fail(e.getMessage());
    }
    tariffDao = DaoFactory.getTariffDao();
    tariff = new Tariff();
    tariff.setLang("en");
    tariff.setPrice(5);
    tariff.setTransferSpeed(216);
    tariff.setName("testtariff");
    tariff.setReceivingSpeed(564);
    tariff.setDescription("testDescription");
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
  public void createTariff() {

    try {
      int initCount = tariffDao.countRecords();
      tariffDao.create(tariff);
      int resultCount = tariffDao.countRecords();
      assertEquals(initCount, (resultCount - 1));
    } catch (DaoException e) {
      fail(e.getMessage());
    }

  }

  @Test
  void updateTest() {
    try {
      tariff.setTariffId(1);
      tariffDao.update(tariff);
    } catch (DaoException e) {
      e.printStackTrace();
    }
  }

  @Test
  void findLimited() {
    int start = 1;
    int end = 3;
    try {
      List<Tariff> tariffs = tariffDao.findLimited(1, 3, "en");
      assertEquals(tariffs.size(), end - start);
    } catch (DaoException e) {
      fail(e.getMessage());
    }
  }
}
