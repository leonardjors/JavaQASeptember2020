import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SampleTest {

    private static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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










