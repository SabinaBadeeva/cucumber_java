package qualitSandBar;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExoticProduct extends BasePage {

    @Test
    @Description("При успешном добавлении нового товара ОВОЩ с актиивным чекбоксом экзотический" +
            " товар отображается в таблице с значениями: " +
            "название товара, " +
            " выбран тип товара Овощ с атрибутом VEGETABLE," +
            " чекбокс выбран = true " +
            "После удаления товар отсутствует ")
    public void addVegetableExoticVisible() throws InterruptedException {
        setProductPage();
        //Добавить товар
        food.setButtonAdd();
        food.addProduct("Пепино");
        food.choiceTypeProductVeget();
        //Проверить,что выбран Овощ (по атрибуту VEGETABLE)
        String actualName = food.getTypeProduct();
        assertEquals(actualName, "VEGETABLE", "type not vegetable");
        food.tapCheckBox();
        //Проверить, что активен чекбокс Экзотический
        boolean isSelected = food.getCheckBoxValue();
        boolean expeсted = true;
        assertEquals(expeсted, isSelected, "checkBox is not active");
        //Нажать на кнопку сохранить
       food.setButtonSave();
        //Проверить, что Пепино добавился в таблицу
       food.getTextAddProductName("Пепино");
        //Проверить,что добавился Овощ
        food.getTextAddProductType("Овощ");
        //Удалить добавленный товар
        specification();
        quaLitNavBar.resetData();
        // Получить  список товаров, убедиться, что добавленного товара нет
        specification();
        food.getListProduct();
    }

    @Test
    @Description("При успешном добавлении нового товара ФРУКТ с актиивным чекбоксом экзотический проверить,что" +
            "товар отображается в таблице с значениями:" +
            " - название товара, " +
            " - выбран тип товара Овощ с атрибутом FRUIT," +
            " - чекбокс выбран = true " +
            "После удаления товар отсутствует")
    public void addFruitExoticVisible() throws InterruptedException {
        setProductPage();
        // Добавить товар
        food.setButtonAdd();
        food.addProduct("Арбуз");
        food.choiceTypeProductFruit();
        //Проверить,что выбран Фрукт (по атрибуту FRUIT)
        String actualName = food.getTypeProduct();
        assertEquals(actualName, "FRUIT", "type is not fruit");
        //Нажать на чекбокс Экзотический
       food.tapCheckBox();
        //Проверить, что активен чекбокс Экзотический
        boolean isSelected = food.getCheckBoxValue();
        boolean expeсted = true;
        assertEquals(expeсted, isSelected, "checkBox is not active");
        food.setButtonSave();
        //Проверить, что Арбуз добавился в таблицу
       food.getTextAddProductName("Арбуз");
        //Проверить,что добавился Фрукт
        food.getTextAddProductType("Фрукт");
        // Удалить добавленный товар
        specification();
        quaLitNavBar.resetData();
        // Получить список товаров, убедиться, что добавденного товара нет
        specification();
        food.getListProduct();
    }

}


