import com.epam.provider.util.Localizer;
import com.epam.provider.util.resource.ResourceConstants;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class LocalizerTest {

    public static String EN_LOC = "en";
    public static String RU_LOC = "ru";

    @Test
    void getLocalizeTextTest() {
        Localizer localizer = new Localizer();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
        String text1 = null;
        try {
            text1 = localizer.getLocalizedText(EN_LOC, ResourceConstants.MESSAGE_KEY_BUTTON_LOGIN);
            String text2 = localizer.getLocalizedText(RU_LOC, ResourceConstants.MESSAGE_KEY_BUTTON_LOGIN);
            assertNotEquals(text1, text2);
        } catch (UnsupportedEncodingException e) {
            fail(e.getMessage());
        }

    }
}
