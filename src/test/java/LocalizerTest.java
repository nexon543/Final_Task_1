import com.epam.provider.util.resource.ResourceConstants;
import com.epam.provider.util.resource.ResourceManager;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class LocalizerTest {

    public static String EN_LOC = "en";
    public static String RU_LOC = "ru";

    @Test
    void getLocalizedTextTest() {
            String text1 = ResourceManager.getMessage( ResourceConstants.M_BUTTON_LOGIN, EN_LOC);
            String text2 = ResourceManager.getMessage( ResourceConstants.M_BUTTON_LOGIN, RU_LOC);
            assertNotEquals(text1, text2);
    }
}
