package ibs.steps.qualitSteps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Step;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static util.PopConst.*;

public class Hooks {
    public static ChromeDriver driver;

    @Before ()
    @Step("Подключаемся к браузеру")
    public void openPage() {
        System.setProperty(PATH_CHROME_DRIVER_WINDOWS, CHROME_WINDOWS);
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(URL);
    }

    @After()
    @Step("закрыть браузер")
    public void tearDown() {
        driver.quit();
    }
}
