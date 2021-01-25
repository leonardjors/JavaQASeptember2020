import config.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.MyProfilePage;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HomeWork4 {
    private WebDriver driver;

    private final Logger logger = LogManager.getLogger(HomeWork4.class);
    private final String latinName = "Bob";
    private final String latinLastName = "Kiwi";
    private final String blogName = "Bob22";
    private final String viberNumber = "777755555";
    private final String VKcontactInfo = "blblblbl";

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
    public void testIfDataIsSavedCorrectly() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);

        //Открывает главную страницу Otus
        mainPage.openMainPage();

        //Логинит юзера
        mainPage.loginUser();

        //Открывает профиль пользователя
        mainPage.openMyProfile();

        //Передает driver в MyProfile class
        MyProfilePage myProfilePage = new MyProfilePage(driver);

        //Заполняет "имя" латиницей
        myProfilePage.fillInLatinName(latinName);

        //Заполняет "фамилию" латиницей
        myProfilePage.fillInLatinLastName(latinLastName);

        //Заполняет поле "имя в блоге"
        myProfilePage.fillInBlogName(blogName);

        //Cкроллит до секции "Контактная информация"
        myProfilePage.scrollToContactInfo();

        //Добавляет "Viber" контакт
        myProfilePage.addViberContactInfo(viberNumber);

        //Добавляет "VK" контакт
        myProfilePage.addVKContactInfo(VKcontactInfo);

        //Скроллит до элемента "Cохранить"
        myProfilePage.scrollToSaveButton();

        //Сохраняет заполненые личные данные
        myProfilePage.savePersonalInfo();

        //Удаляет cookies
        clearUpCookies(driver);

        mainPage = new MainPage(driver);

        mainPage.openMainPage();

        //Логинит юзера
        mainPage.loginUser();

        //Открывает профиль пользователя
        mainPage.openMyProfile();

        //Передает driver в MyProfile class
        myProfilePage = new MyProfilePage(driver);

        System.out.println(myProfilePage.getLatinName());

        assertThat(myProfilePage.getLatinName()).isEqualTo(latinName);

        assertThat(myProfilePage.getLatinLastName()).isEqualTo(latinLastName);

        assertThat(myProfilePage.getBlogName()).isEqualTo(blogName);

        assertThat(myProfilePage.getViberContactInfo()).isEqualTo(viberNumber);

        assertThat(myProfilePage.getVKContactInfo()).isEqualTo(VKcontactInfo);

        logger.info("Данные провалидированы");

        myProfilePage.clearLatinName();

        myProfilePage.clearLatinLastName();

        myProfilePage.clearBlogName();

        myProfilePage.scrollToContactInfoToDelete();

        myProfilePage.deleteViberContact();

        myProfilePage.deleteVKContact();

        myProfilePage.scrollToSaveButton();

        myProfilePage.savePersonalInfo();

    }


    @After

    public void tearDown() {


        logger.info("Тест завершился");

        if (driver != null) {
            driver.quit();
        }

    }

    private void clearUpCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();
        logger.info("Cookies deleted");
    }
}
