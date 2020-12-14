import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private WebDriver driver;
    private Logger logger = LogManager.getLogger(MainPage.class);
    String baseURL = cfg.otusURL();
    String username = cfg.username();
    String password = cfg.password();
    By otusMainPageLogo = By.cssSelector(".header2__logo-img");
    By loginAndRegisterButton = By.cssSelector(".header2__auth");
    By otusLoginAndRegisterLogo = By.cssSelector(".ic.new-ic-logo-with-text.new-log-reg__logo");
    By usernameField = By.cssSelector(".new-log-reg__login  form[method='post']  input[name='email']");
    By passwordField = By.cssSelector("input[name='password']");
    By loginButton = By.cssSelector(".new-log-reg__login  form[method='post']  .new-button.new-button_blue.new-button_full.new-button_md0");
    By userLogo = By.cssSelector(".header2-menu__icon-img.ic-blog-default-avatar");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    public MainPage openMainPage() {
        driver.get("baseURL");
        waitElementIsDisplayed(otusMainPageLogo);
        logger.info("На главной странице OTUS");
    }


    public MainPage loginUser(String username, String password) {
        clickOnVisibleElement(loginAndRegisterButton);
        waitElementIsDisplayed(otusLoginAndRegisterLogo);
        logger.info("На форме регистрации и логина");
        driver.findElement(usernameField).sendKeys(username);
        logger.info("Введено имя пользователя");
        driver.findElement(passwordField).sendKeys(password);
        logger.info("Введен пароль");
        clickOnVisibleElement(loginButton);
        waitElementIsDisplayed(userLogo);
        logger.info("Пользователь вошел в систему");


    }


    public MyProfilePage openMyAccount() {


    }


    private void clickOnVisibleElement(By locator) {
        waitElementIsDisplayed(locator);
        driver.findElement(locator).click();
    }

    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeout) {
        timeout = timeout != null ? timeout : 10;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }

    private void waitElementIsDisplayed(By locator, Integer... timeout) {
        waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                (timeout.length > 0 ? timeout[0] : null));
    }


}
