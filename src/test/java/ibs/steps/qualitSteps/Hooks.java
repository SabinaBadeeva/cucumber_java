package ibs.steps.qualitSteps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBrowserVersion("109.0");
        chromeOptions.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        chromeOptions.addArguments("--no-sandbox");

        driver = new RemoteWebDriver(
                URI.create("http://149.154.71.152:4444/wd/hub").toURL(),
                chromeOptions
        );

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
