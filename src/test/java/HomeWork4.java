import config.ServerConfig;
import config.WebDriverFactory;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.MyProfilePage;

import static org.assertj.core.api.Assertions.assertThat;

public class HomeWork4 {
    private WebDriver driver;

    private Logger logger = LogManager.getLogger(HomeWork4.class);
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private String latinName = "Bob";
    private String latinLastName = "Kiwi";
    private String blogName = "Bob22";
    private String viberNumber = "777755555";
    private String VKcontactInfo = "blblblbl";

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

//        //Запонляет поле "Дата рождения"
//        myProfilePage.fillInBirthdate();
//        Thread.sleep(3000);
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


    }


    /*@Test
    public void validatePersonalInfo() {
        //Открывает главную страницу Otus
        MainPage mainPage = new MainPage(driver);

        mainPage.openMainPage();

        //Логинит юзера
        mainPage.loginUser();

        //Открывает профиль пользователя
        mainPage.openMyProfile();

        //Передает driver в MyProfile class
        MyProfilePage myProfilePage = new MyProfilePage(driver);

        System.out.println(myProfilePage.getLatinName());

        assertThat(myProfilePage.getLatinName()).isEqualTo(latinName);


    }*/

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
