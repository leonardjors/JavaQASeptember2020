import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class YandexTest {

    private WebDriver driver;
    private Logger logger = LogManager.getLogger(YandexTest.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @Before
    public void setUp() {
        String browserName = System.getProperty("browser");
        System.out.println(browserName);

        if (browserName == null) {
            browserName = "Chrome";
        }


        driver = WebDriverFactory.create(browserName);
        logger.info("Драйвер поднят");
        driver.manage().window().maximize();


    }

    @Test
    public void smartphonesComparison() {
        String baseUrl = cfg.yandexURL();
        String SAMSUNG = "samsung";
        String XIAOMI = "xiaomi";
        By yandexMarketMainPageLogo = By.xpath("//div[@data-apiary-widget-id='/header/logo']");
        By electronicsMainMenuItem = By.xpath("//span[text() = 'Электроника']");
        By electronicsPageHeader = By.xpath("//h1[text()='Электроника']");
        By smartPhoneSideMenuItem = By.xpath("//ul//a[text() = 'Смартфоны']");
        By manufacturerSamsungFilterItem = By.cssSelector("body._12JXUyMYfl:nth-child(2) div._111XIPXNiH.main:nth-child(4) div.tamefSzMtq:nth-child(3) aside._2SUmDOuJb1 div._1dg-mVVfwq:nth-child(3) div._1vMoBTNhsM div._178jz2CyDL div._3_phr-spJh:nth-child(3) div._2Hue1bCg-N fieldset._3M70uokkTS ul._2y67xS5HuR li._1-l0XiE_ze:nth-child(9) div._16hsbhrgAf a._2RDCAZB4Gk label._2IwbFpEZn7._1e7iX1B2oW > div.LhMupC0dLR");
        By manufacturerXiaomiFilterItem = By.xpath("//fieldset//span[text()='Xiaomi']");
        By smartPhonesPageHeader = By.xpath("//h1");
        By articlePhoneTitle = By.xpath("//h3/a");
        By searchResultBlocker = By.xpath("//div[contains(@class, '_2LvbieS_AO')]");
        By byPriceFilter = By.xpath("//button[text()='по цене']");
        By addToComparePopUpCloseBtn = By.xpath("//div[@data-apiary-widget-id='/content/popupInformer']//button");
        By addToComparePopUp = By.xpath("//div[contains(text(), 'добавлен к сравнению')]");
        By addToComparePopUpCompareBtn = By.xpath("//a//span[text()='Сравнить']");
        By smartPhonesComparePageHeader = By.xpath("//div[text()='Сравнение товаров']");
        By smartPhoneCompareName = By.xpath("//div[@class='e910RKmlsj']//a");
        By cookiesPopup = By.xpath("//button[contains(text(),'Принять')]");


        driver.get(baseUrl);
        waitElementIsDisplayed(yandexMarketMainPageLogo);
        logger.info(" на главной 'Яндекс.Маркет'");

        clickOnVisibleElement(cookiesPopup);
        clickOnVisibleElement(electronicsMainMenuItem);
        waitElementIsDisplayed(electronicsPageHeader);
        logger.info(" на странице 'Электроника'");
        clickOnVisibleElement(smartPhoneSideMenuItem);
        waitElementIsDisplayed(smartPhonesPageHeader);
        logger.info(" на странице 'Смартфоны'");

        clickOnVisibleElement(manufacturerSamsungFilterItem);
        clickOnVisibleElement(manufacturerXiaomiFilterItem);
        waitBlockerToDisappear(searchResultBlocker);
        clickOnVisibleElement(byPriceFilter);
        waitBlockerToDisappear(searchResultBlocker);

        HashMap<String, String> samsungData = getPhoneDataFromListItem(articlePhoneTitle, SAMSUNG);
        clickOnNotVisibleElement(customLocatorAddToCompare(samsungData.get("index")));
        waitElementIsDisplayed(addToComparePopUp);
        logger.info(String.format(" : %s", samsungData.get("phoneData")));
        assertThat(driver.findElement(addToComparePopUp).getText()).contains(samsungData.get("phoneData"));
        clickOnVisibleElement(addToComparePopUpCloseBtn);

        HashMap<String, String> xiaomiData = getPhoneDataFromListItem(articlePhoneTitle, XIAOMI);
        clickOnNotVisibleElement(customLocatorAddToCompare(xiaomiData.get("index")));
        waitElementIsDisplayed(addToComparePopUp);
        logger.info(String.format(" : %s", xiaomiData.get("phoneData")));
        assertThat(driver.findElement(addToComparePopUp).getText()).contains(xiaomiData.get("phoneData"));

        clickOnVisibleElement(addToComparePopUpCompareBtn);
        waitElementIsDisplayed(smartPhonesComparePageHeader);
        logger.info("на странице 'Сравнить товар'");

        waitElementIsDisplayed(smartPhoneCompareName);
        List<String> comparisonList = convertData(driver.findElements(smartPhoneCompareName));
        assertThat(comparisonList).hasSize(2)
                .contains(samsungData.get("phoneData"), xiaomiData.get("phoneData"));
    }

    @After
    public void setDown() {
        logger.info("тест завершился");
        if (driver != null) {
            driver.quit();
        }
    }

    private void clickOnVisibleElement(By locator) {
        waitElementIsDisplayed(locator);
        driver.findElement(locator).click();
    }

    private void clickOnNotVisibleElement(By locator) {
        driver.findElement(locator).click();
    }

    private void waitBlockerToDisappear(By locator) {
        waitElementIsDisplayed(locator);
        waitElementIsNotDisplayed(locator);
    }

    private HashMap<String, String> getPhoneDataFromListItem(By namesLocator, String smartPhoneName) {
        HashMap<String, String> map = new HashMap<>();
        List<WebElement> phoneNames = driver.findElements(namesLocator);
        for (WebElement phoneName : phoneNames) {
            if (phoneName.getText().toLowerCase().contains(smartPhoneName)) {
                Integer index = phoneNames.indexOf(phoneName) + 1;
                map.put("index", index.toString());
                map.put("phoneData", phoneName.getText());
                break;
            }
        }
        return map;
    }


    private List<String> convertData(List<WebElement> list) {
        List<String> strList = new ArrayList<>();
        for (WebElement listItem : list) {
            strList.add(listItem.getText());
        }
        return strList;
    }

    private void waitElementIsDisplayed(By locator, Integer... timeout) {
        waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                (timeout.length > 0 ? timeout[0] : null));
    }

    private void waitElementIsNotDisplayed(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeout) {
        timeout = timeout != null ? timeout : 10;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }

    private By customLocatorAddToCompare(String index) {
        return By.xpath("(//div[@class='_1CXljk9rtd'])" + String.format("[%s]", index));
    }

}
