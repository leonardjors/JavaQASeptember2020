import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SampleTest {







    private static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    //private String browserName = System.getProperty("Dbrowser");

    private static String parseBrowserName(String input) {
        String result = null;
        String pattern = "\\W*(\\w+)\\W*";
        Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = r.matcher(input);

        if (m.matches()) {
            result = m.group(1);
        }

        return result;


    }

    @Before
    public void setUp() {
        String browserName = parseBrowserName(System.getProperty("browser"));
        System.out.println(browserName);

        if (browserName == null) {
            browserName = "Chrome";
        }



        driver = WebDriverFactory.create(browserName);
        logger.info("Драйвер поднят");
    }


    @Test
    public void checkTitle() {

        driver.get(cfg.url());
        logger.info("Открыта страница отус");

        String actualTitle = driver.getTitle();
        logger.debug("Title страницы - " + actualTitle);

        Assert.assertEquals(cfg.expectedTitile(), actualTitle);

        logger.info("Title страницы успешно валидирован");




    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}














