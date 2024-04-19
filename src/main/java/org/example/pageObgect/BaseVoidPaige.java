package org.example.pageObgect;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


/**
 * Базовый класс общих методов для страниц Food и QualitNavBar
 */
public class BaseVoidPaige {

    private WebDriver driver;

    /**
     * Функция, позволяющая производить scroll до элемента с помощью js
     */
    protected WebElement scrollToElementJs(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }
    /**
     * Явное ожидание состояния clickable элемента
     */
    protected WebElement waitUtilElementToBeClickable(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(element));
    }
    /**
     * Явное ожидание того что элемент станет видимым
     */
    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated((By) element));
    }
    /**
     * Общий метод  заполнения полей ввода
     */
    protected void fillInputField(WebElement element, String value) {
        waitUtilElementToBeClickable(element).click();
        element.sendKeys(value);
    }


}
