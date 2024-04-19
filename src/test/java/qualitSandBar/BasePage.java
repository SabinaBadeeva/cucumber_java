package qualitSandBar;

import ibs.steps.qualitSteps.QualitSteps;
import io.qameta.allure.Step;
import org.example.pageObgect.Food;
import org.example.pageObgect.QuaLitNavBar;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;

import static util.PopConst.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BasePage {
    public static ChromeDriver driver;
    public Food food;
    public QuaLitNavBar quaLitNavBar;
    public QualitSteps qualitSteps;
    private final Properties properties = new Properties();
    private static BasePage INSTANCE = null;

    @BeforeAll
    public void openPage() throws MalformedURLException {
        System.setProperty(PATH_CHROME_DRIVER_WINDOWS, CHROME_WINDOWS);
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(URL);
        food = new Food(driver);
        quaLitNavBar = new QuaLitNavBar(driver);
        qualitSteps = new QualitSteps();
    }

    public void setProductPage() {
        //Нажать на Кнопку Песочница
        quaLitNavBar.setNavbarDropdown();
        //Нажать на кнопку Товары
        quaLitNavBar.setProductsDropdown();
    }

    //нажать на кнопку Песочница
    @Step("Нажимаем на кнопку Песочница")
    public void setSandBox() {
        quaLitNavBar.setNavbarDropdown();
    }

    public static void specification() throws InterruptedException {
        Thread.sleep(2000);
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }
}

