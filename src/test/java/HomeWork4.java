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


public class HomeWork4 {
    private WebDriver driver;

    private Logger logger = LogManager.getLogger(HomeWork4.class);
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
    public void otusTestLogin() throws InterruptedException {
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
        myProfilePage.fillInLatinName();

        //Заполняет "фамилию" латиницей
        myProfilePage.fillInLatinLastName();

        //Заполняет поле "имя в блоге"
        myProfilePage.fillInBlogName();

//        //Запонляет поле "Дата рождения"
//        myProfilePage.fillInBirthdate();
//        Thread.sleep(3000);
        //Cкроллит до секции "Контактная информация"
        myProfilePage.scrollToContactInfo();

        //Добавляет "Viber" контакт
        myProfilePage.addViberContactInfo();

        //Добавляет "VK" контакт
        myProfilePage.addVKContactInfo();

        //Скроллит до элемента "Cохранить"
        myProfilePage.scrollToSaveButton();

        //Сохраняет заполненые личные данные
        myProfilePage.savePersonalInfo();
    }


    @After

    public void tearDown() {
        logger.info("Тест завершился");

        if (driver != null) {
            driver.quit();
        }

    }
}
