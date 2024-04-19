package org.example.pageObgect;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class QuaLitNavBar extends BaseVoidPaige {
    private WebDriver driver;

    public QuaLitNavBar(WebDriver driver) {
        this.driver = driver;
    }

    private By navbarDropdown = By.id("navbarDropdown"); // локатор для выпадающего списка Песочница
    private By productsDropdown = By.linkText("Товары"); // локатор для перехода в Товары
    private By resetDropdown = By.linkText("Сброс данных"); // локатор для Сброса данных

    // Метод нажатия на выпадающий список Песочница
    @Step("Нажимаем на кнопку песочница")
    public void setNavbarDropdown() {
        driver.findElement(navbarDropdown).click();
    }

    // Метод выбора в выпадающем списке пункта Товары
    @Step ("Нажать на Товары")
    public void setProductsDropdown() {
        driver.findElement(productsDropdown).click();
    }

    //Метод выбора в выпадающем списке пункта Сброс данных
    public void setResetDropdown() {
        driver.findElement(resetDropdown).click();
    }
    @Step("Удалить добавленный товар")
    public void resetData() {
        setNavbarDropdown();
        setResetDropdown();
    }
}
