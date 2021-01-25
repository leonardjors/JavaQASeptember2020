package pages;

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

    By latinNameElement = By.cssSelector("#id_fname_latin");
    By latinLastNameElement = By.cssSelector("#id_lname_latin");
    By blogNameElement = By.cssSelector("#id_blog_name");
    By birthdateElement = By.cssSelector("input[name='date_of_birth']");
    By addButtonElement1 = By.xpath("//button[contains(text(),'Добавить')]");
    By addButtonElement2 = By.cssSelector(".js-formset > .js-formset-add.js-lk-cv-custom-select-add.lk-cv-block__action.lk-cv-block__action_md-no-spacing");
    By contactOptionDropDownElement1 = By.cssSelector(".input_straight-bottom-right");
    By viberElementFromDropDown = By.cssSelector("button[title='Viber']");
    By viberInputFieldElement = By.cssSelector("input#id_contact-0-value");
    By contactOptionDropDownElement2 = By.cssSelector("div.body-wrapper:nth-child(2) div.body.drawer.body_not-subscribed.drawer--right div.js-lk-cv:nth-child(8) div.container.container-padding-bottom div.container__row div.container__col.container__col_9.container__col_md-8.container__col_sm-12.container__col_border-left.lk-rightbar.print-block.print-wide:nth-child(2) div.container-padding-bottom div.container__row:nth-child(2) div.container__col.container__col_12:nth-child(3) div.lk-cv-block:nth-child(2) div.js-formset div.lk-cv-block__line.js-formset-items:nth-child(5) div.container__row.js-formset-row:nth-child(3) div.container__col.container__col_9.container__col_ssm-12:nth-child(2) div.container__row div.container__col.container__col_12.container__col_middle div.select.lk-cv-block__input.lk-cv-block__input_3.lk-cv-block__input_md-4.js-lk-cv-custom-select label:nth-child(1) > div.input.input_full.lk-cv-block__input.input_straight-bottom-right.input_straight-top-right.input_no-border-right.lk-cv-block__input_fake.lk-cv-block__input_select-fake.js-custom-select-presentation");
    By vkElementFromDropDown = By.cssSelector(".js-formset > div > div:nth-of-type(2) .js-lk-cv-custom-select.lk-cv-block__input.lk-cv-block__input_3.lk-cv-block__input_md-4.select button[title='VK']");
    By vkInputFieldElement = By.cssSelector("[data-num='1'] [class='input input_straight-top-left input_straight-bottom-left lk-cv-block__input lk-cv-block__input_9 lk-cv-block__input_md-8']");
    By saveButtonElement = By.cssSelector("button[name='continue']");


    public MyProfilePage(WebDriver driver) {
        this.driver = driver;
    }


    public void fillInLatinName(String latinName) {
        clickOnVisibleElement(latinNameElement);
        driver.findElement(latinNameElement).sendKeys(latinName);

        logger.info("Добавлено имя латиницей");

    }

    public String getLatinName() {
        return driver.findElement(latinNameElement).getAttribute("value");


    }

    public void clearLatinName() {
        driver.findElement(latinNameElement).clear();

    }

    public void fillInLatinLastName(String latinLastName) {
        clickOnVisibleElement(latinLastNameElement);
        driver.findElement(latinLastNameElement).sendKeys(latinLastName);
        logger.info("Добавлена фамилия латиницей");
    }

    public String getLatinLastName() {
        return driver.findElement(latinLastNameElement).getAttribute("value");
    }

    public void clearLatinLastName() {
        driver.findElement(latinLastNameElement).clear();
    }

    public void fillInBlogName(String blogName) {
        clickOnVisibleElement(blogNameElement);
        driver.findElement(blogNameElement).sendKeys(blogName);
        logger.info("Добавлено имя в блоге");
    }

    public String getBlogName() {
        return driver.findElement(blogNameElement).getAttribute("value");
    }

    public void clearBlogName() {
        driver.findElement(blogNameElement).clear();
    }

    public void addViberContactInfo(String viberNumber) {

        scrollToContactInfo();
        clickOnVisibleElement(addButtonElement1);
        clickOnVisibleElement(contactOptionDropDownElement1);
        clickOnVisibleElement(viberElementFromDropDown);
        driver.findElement(viberInputFieldElement).sendKeys(viberNumber);
        logger.info("Добавлен контакт \\viber\\");
    }

    public String getViberContactInfo() {
        return driver.findElement(viberInputFieldElement).getAttribute("value");
    }

    public void addVKContactInfo(String vkContactInfo) {

        //clickOnVisibleElement(addButtonElement2);
        clickOnVisibleElement(contactOptionDropDownElement2);
        clickOnVisibleElement(vkElementFromDropDown);
        driver.findElement(vkInputFieldElement).sendKeys(vkContactInfo);
        logger.info("Добавлен контакт \\VK\\");
    }

    public String getVKContactInfo() {
        return driver.findElement(vkInputFieldElement).getAttribute("value");
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

    public void clickOnVisibleElement(By locator) {
        waitElementIsDisplayed(locator);
        driver.findElement(locator).click();
    }


    public void waitFor(ExpectedCondition<WebElement> condition, Integer timeout) {
        timeout = timeout != null ? timeout : 10;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }

    public void waitElementIsDisplayed(By locator, Integer... timeout) {
        waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                (timeout.length > 0 ? timeout[0] : null));
    }


    public void waitElementIsNotDisplayed(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOf(locator));

    }


}


