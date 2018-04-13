import com.epam.provider.dao.pool.ConnectionPool;
import com.epam.provider.dao.pool.ConnectionPoolException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.fail;

public class ConnectionPoolTest {

    @BeforeAll
    public static void setUp() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.initPoolData();
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

    @AfterAll
    static void tearDownAll() {
        try {
            ConnectionPool.getInstance().clearConnectionQueue();
        } catch (ConnectionPoolException e) {
            fail(e.getMessage());
        }
    }
}
