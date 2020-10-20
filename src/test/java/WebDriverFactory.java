import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class WebDriverFactory {
    public static WebDriver create(String webDriverName, Object options) {

        WebDrivers desiredDriver = WebDrivers.valueOf(webDriverName.toUpperCase());

        switch (desiredDriver) {

            case CHROME:
                return getChromeDriver((ChromeOptions) options);

            case FIREFOX:
                return getFirefoxDriver((FirefoxOptions) options);


            case IE:
                return getInternetExplorerDriver((InternetExplorerOptions) options);
        }
        throw new IllegalArgumentException(String.format("%s is not supported", webDriverName));
    }


    public static WebDriver create(String webDriverName) {


        return create(webDriverName, null);


    }

    private static ChromeDriver getChromeDriver(ChromeOptions options) {
        ChromeDriver driver;

        WebDriverManager.chromedriver().setup();

        if (options == null) {

            driver = new ChromeDriver();

        } else {
            driver = new ChromeDriver(options);
        }


        return driver;


    }

    private static FirefoxDriver getFirefoxDriver(FirefoxOptions options) {

        FirefoxDriver driver;

        WebDriverManager.firefoxdriver().setup();

        if (options == null) {

            driver = new FirefoxDriver();

        } else {
            driver = new FirefoxDriver(options);
        }


        return driver;


    }

    private static InternetExplorerDriver getInternetExplorerDriver(InternetExplorerOptions options) {

        InternetExplorerDriver driver;

        WebDriverManager.iedriver().setup();

        if (options == null) {

            driver = new InternetExplorerDriver();

        } else {
            driver = new InternetExplorerDriver(options);
        }


        return driver;


    }

    public enum WebDrivers {
        CHROME,
        FIREFOX,
        IE

    }

}
