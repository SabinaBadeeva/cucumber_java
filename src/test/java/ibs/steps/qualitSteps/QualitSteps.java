package ibs.steps.qualitSteps;

import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Также;
import io.cucumber.java.ru.Тогда;
import org.example.pageObgect.Food;
import org.example.pageObgect.QuaLitNavBar;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static qualitSandBar.BasePage.specification;

public class QualitSteps {
    WebDriver driver = Hooks.driver;
    QuaLitNavBar quaLitNavBar = new QuaLitNavBar(driver);
    Food food = new Food(driver);

    public QualitSteps() {
    }

    @Допустим("Нажимаем на кнопку Песочница")
    public void нажимаемНаКнопкуПесочница()  {

        quaLitNavBar.setNavbarDropdown();
    }

    @Допустим("Нажимаем на кнопку Товары")
    public void нажимаемНаКнопкуТовары() {
        quaLitNavBar.setProductsDropdown();
    }

    @Когда("На главной странице нажимаем кнопку Добавить")
    public void наГлавнойСтраницеНажимаемКнопкуДобавить() {
        food.setButtonAdd();
    }

    @Также("В поле Наименование вводим данные товара {string}")
    public void вПолеНаименованиеВводимДанныеТовара(String name) {
        food.addProduct(name);

    }

    @Также("В выпадающем списке выбираем тип Товара")
    public void вВыпадающемСпискеВыбираемТипТовара() {
        food.choiceTypeProductFruit();
    }

    @Тогда("Проверяем,что выбран Фрукт по атрибуту {string}")
    public void проверяемЧтоВыбранФруктПоАтрибуту(String arg0) {
        String actualName = food.getTypeProduct();
        assertEquals(actualName, arg0, "type is not fruit");
    }

    @Также("Нажать на чекбокс Экзотический")
    public void нажатьНаЧекбоксЭкзотический() {
        food.tapCheckBox();

    }

    @Тогда("Проверить, что активен чекбокс Экзотический")
    public void проверитьЧтоАктивенЧекбоксЭкзотический() {
        boolean isSelected = food.getCheckBoxValue();
        boolean expeсted = true;
        assertEquals(expeсted, isSelected, "checkBox is not active");
    }

    @Также("Нажать на кнопу Сохранить")
    public void нажатьНаКнопуСохранить() {
        food.setButtonSave();
    }

    @Тогда("Проверить, что {string} добавился в таблицу")
    public void проверитьЧтоДобавилсяВТаблицу(String name) {
        food.getTextAddProductName(name);
    }

    @Также("Удалить добавленный товар")
    public void удалитьДобавленныйТовар() throws InterruptedException {
        specification();
        quaLitNavBar.resetData();
    }

    @Тогда("Получить список товаров, убедиться, что добавленного товара нет")
    public void получитьСписокТоваровУбедитьсяЧтоДобавленногоТовараНет() throws InterruptedException {
        specification();
        food.getListProduct();
    }
}
