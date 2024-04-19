package org.example.pageObgect;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Food extends BaseVoidPaige {
    private WebDriver driver;

    public Food(WebDriver driver) {
        this.driver = driver;
    }

    private By buttonAdd = By.xpath(".//button[text()='Добавить']"); // локатор кнопки Добавить
    private By inputNameProduct = By.xpath(".//input[@placeholder ='Наименование']"); // локатор инпут-поля Наименование
    private By selectType = By.xpath(".//div/select[@id = 'type']"); // локатор селектора выбора Овощ/Фрукт
    private By selectTypeProduct = By.id("type");
    private By selectVegetable = By.xpath(".//div/select[@id = 'type']/option[text()='Овощ']");// локатор для выбора значения Овощ
    private By selectFruit = By.xpath(".//div/select[@id = 'type']/option[text()='Фрукт']");// локатор для выбора значения Фрукт
    private By checkBoxChoice = By.id("exotic");// локатор  чекбокса Экзотический
    private By buttonSave = By.xpath(".//button[text()='Сохранить']");// локатор кнопки Сохранить
    private By addProductName = By.xpath("//div/table/tbody/tr[5]/td[1]"); //локатор добавленного товара по Названию
    private By addProductType = By.xpath("//div/table/tbody/tr[5]/td[2]"); //локатор добавленного товара по Названию


    //Метод нажатия на кнопку Добавить
    @Step("На главной странице нажимаем кнопку Добавить")
    public void setButtonAdd() {
        driver.findElement(buttonAdd).click();
    }

    //Метод добавления товаров в инпут Наименование
    @Step("В поле Наименование вводим данные товара ")
    public void addProduct(String product) {
        driver.findElement(inputNameProduct).sendKeys(product);
    }

    // Метод выбора типа продукта
    public void choiceTypeProduct(By select) {

        driver.findElement(selectType).click();
    }

    // Метод выбора типа Овощ
    public void choiceVegetable() {
        driver.findElement(selectVegetable).click();
    }

    public void choiceTypeProductVeget() {
        choiceTypeProduct(selectVegetable);
        choiceVegetable();
    }

    // Метод выбора типа Фрукт
    public void choiceFruit() {
        driver.findElement(selectFruit).click();
    }
    @Step("В выпадающем списке выбираем тип Товара")
    public void choiceTypeProductFruit() {
        choiceTypeProduct(selectFruit);
        choiceFruit();
    }

    //Метод возврата атрибута выбранного типа продукта
    @Step("Проверяем,что выбран Фрукт по атрибуту FRUIT")
    public String getTypeProduct() {
        return driver.findElement(selectTypeProduct).getAttribute("value");
    }

    //Метод выбора чекбокса Экзотический
    @Step("Нажать на чекбокс Экзотический")
    public void tapCheckBox() {
        driver.findElement(checkBoxChoice).click();
    }

    // Метод проверяющий,что чекбокс Экзотический активен
    @Step("Проверить, что активен чекбокс Экзотический")
    public boolean getCheckBoxValue() {
        boolean webElement = driver.findElement(checkBoxChoice).isSelected();
        return webElement;
    }

    //Метод нажать на кнопку Сохранить
    @Step("Нажать на кнопу Сохранить")
    public void setButtonSave() {
        driver.findElement(buttonSave).click();
    }

    //Проверить, название добавленного товара
    @Step("Проверить, что товар добавился в таблицу")
    public boolean getTextAddProductName(String dataTextProduct) {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElementLocated(addProductName, dataTextProduct));
    }

    // Проверить тип названия добавленного товара
    public void getTextAddProductType(String dataTextProduct) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElementLocated(addProductType, dataTextProduct));
    }

    // Вывод  изначального списка товаров
    @Step("Получить список товаров, убедиться, что добавленного товара нет")
    public List<String> getListProduct() {

        WebElement table = driver.findElement(By.xpath("//div/table/tbody"));
        List<WebElement> rows = table.findElements(By.xpath("//tr"));
        java.util.Iterator<WebElement> iter = rows.iterator();
        while (iter.hasNext()) {
            WebElement row = iter.next();
            System.out.println(row.getText());
        }
        return Arrays.asList(rows.get(0).getText(),rows.get(1).getText(),rows.get(2).getText());
    }
}
