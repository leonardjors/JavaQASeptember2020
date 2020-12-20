package pages;

import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyProfilePage {
    private WebDriver driver;
    private Logger logger = LogManager.getLogger(pages.MyProfilePage.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    String latinName = cfg.latinName();
    String latinLastName = cfg.latinLastName();
    String blogName = cfg.blogName();
    String birthdate = cfg.birthdate();
    String viberNumber = cfg.viberNumber();
    String vkContactInfo = cfg.VKcontactInfo();
    By latinNameElement = By.cssSelector("#id_fname_latin");
    By latinLastNameElement = By.cssSelector("#id_lname_latin");
    By blogNameElement = By.cssSelector("#id_blog_name");
    By birthdateElement = By.cssSelector("input[name='date_of_birth']");
    By addButtonElement = By.xpath("//button[contains(text(),'Добавить')]");
    By contactOptionDropDownElement1 = By.xpath("/html/body/div[@class='body-wrapper']/div/div[@class='js-lk-cv']/div[@class='container container-padding-bottom']//form[@action='/lk/biography/personal/']//div[@class='container__col container__col_12']/div[2]/div[@class='js-formset']//div[@class='container__row js-formset-row']//div[@class='container__col container__col_12 container__col_middle']/div/label/div");
    By viberElementFromDropDown = By.cssSelector("[data-num='3'] [data-value='viber']");
    By viberInputFieldElement = By.cssSelector("input[name='contact-4-value']");
    By contactOptionDropDownElement2 = By.xpath("/html/body/div[@class='body-wrapper']/div/div[@class='js-lk-cv']/div[@class='container container-padding-bottom']//form[@action='/lk/biography/personal/']//div[@class='container__col container__col_12']/div[2]/div[@class='js-formset']/div/div[12]//div[@class='container__col container__col_12 container__col_middle']/div/label/div");
    By vkElementFromDropDown = By.cssSelector("[data-num='5'] [data-value='vk']");
    By vkInputFieldElement = By.cssSelector("input[name='contact-7-value']");
    By saveButtonElement = By.cssSelector("button[name='continue']");

    public MyProfilePage(WebDriver driver) {
        this.driver = driver;
    }


    public void fillInLatinName() {
        clickOnVisibleElement(latinNameElement);
        driver.findElement(latinNameElement).sendKeys(latinName);

        logger.info("Добавлено имя латиницей");

    }

    public void fillInLatinLastName() {
        clickOnVisibleElement(latinLastNameElement);
        driver.findElement(latinLastNameElement).sendKeys(latinLastName);
        logger.info("Добавлена фамилия латиницей");
    }

    public void fillInBlogName() {
        clickOnVisibleElement(blogNameElement);
        driver.findElement(blogNameElement).sendKeys(blogName);
        logger.info("Добавлено имя в блоге");
    }

    public void fillInBirthdate() {
        driver.findElements(birthdateElement).clear();
        driver.findElement(birthdateElement).sendKeys(birthdate);
        logger.info("Добавлена дата рождения");


    }

    public void addViberContactInfo() {

        scrollToContactInfo();
        clickOnVisibleElement(addButtonElement);
        clickOnVisibleElement(contactOptionDropDownElement1);
        clickOnVisibleElement(viberElementFromDropDown);
        driver.findElement(viberInputFieldElement).sendKeys(viberNumber);
        logger.info("Добавлен контакт \\viber\\");
    }

    public void addVKContactInfo() {

        clickOnVisibleElement(addButtonElement);
        clickOnVisibleElement(contactOptionDropDownElement2);
        clickOnVisibleElement(vkElementFromDropDown);
        driver.findElement(vkInputFieldElement).sendKeys(vkContactInfo);
        logger.info("Добавлен контакт \\VK\\");
    }

    public void savePersonalInfo() {
        scrollToSaveButton();
        clickOnVisibleElement(saveButtonElement);
        logger.info("Личные данные сохранены");

    }

    public void scrollToContactInfo() {
        WebElement webElement = driver.findElement(By.xpath("//p[contains(text(),'Контактная информация')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);


    }

    public void scrollToSaveButton() {
        WebElement webElement = driver.findElement(saveButtonElement);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);


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
