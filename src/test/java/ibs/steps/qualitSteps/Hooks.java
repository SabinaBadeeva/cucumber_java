package ibs.steps.qualitSteps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.Map;

import static util.PopConst.*;

public class Hooks {
    public static WebDriver driver;

    @Before()
    @Step("Подключаемся к браузеру")
    public void openPage() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "109.0");
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", false
        ));

        driver = new RemoteWebDriver(
                URI.create("http://149.154.71.152:4444/wd/hub").toURL(),
                capabilities
        );

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://149.154.71.152:8080/");
    }

    @After()
    @Step("закрыть браузер")
    public void tearDown() {
        driver.quit();
    }
}
