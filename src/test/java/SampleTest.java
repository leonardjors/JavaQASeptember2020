import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SampleTest {

    private static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    //private String browserName = System.getProperty("Dbrowser");

    @BeforeMethod
    public void setUp() {
        String browserName = System.getProperty("browser");
        //if (browserName == null) {
         //   browserName = "Chrome";
       // }

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

    @AfterMethod
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}










