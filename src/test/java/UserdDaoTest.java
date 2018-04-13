import com.epam.provider.dao.DaoException;
import com.epam.provider.dao.ProfileDao;
import com.epam.provider.dao.impl.ProfileDaoImpl;
import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.model.Profile;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.MessageDigest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class UserdDaoTest {

    private final static String LOGIN = "testLogin";
    private final static String PASSWORD = "tesPassword";
    private static String hashedPass;
    private static ProfileDao profileDao;
    private static Profile profile = new Profile();

    @BeforeAll
    static void setUp() {
        profileDao = new ProfileDaoImpl();
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.initPoolData();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(PASSWORD.getBytes("UTF-8"));
            profile.setLogin(LOGIN);
            profile.setRole("client");
            hashedPass = new BigInteger(1, md.digest()).toString(16);
            profile.setPassword(hashedPass);
            profile.setProfileId(1);
            profileDao.create(profile);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testFindUserByLoginPass() {
        try {
            profile = profileDao.findByLoginPass(LOGIN, hashedPass);
            assertNotNull(profile.getProfileId());
        } catch (DaoException e) {
            fail(e.getMessage());
        }
    }

    @AfterAll
    static void tearDownAll() {
        try {
            profileDao.delete(profile.getProfileId());
        } catch (DaoException e) {
            fail(e.getMessage());
        }
    }
}
