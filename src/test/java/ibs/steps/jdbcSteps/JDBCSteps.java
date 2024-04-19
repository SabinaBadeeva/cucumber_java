package ibs.steps.jdbcSteps;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Затем;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.sql.*;
import java.util.Map;

import static jdbc.DBConstant.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JDBCSteps {


    public JDBCSteps() throws SQLException {
    }

    static Connection connection;

        static {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_NAME, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Дано("Просмотр начальных данных по параметрам {string},{string},{string}")
    public void просмотрНачальныхДанныхПоПараметрам(String foodName, String foodType, String exotic) throws SQLException {
        Statement stmt = connection.createStatement();
        String sql = "SELECT * FROM food";
        ResultSet rs = stmt.executeQuery(sql);
        int count = 0;
        while (rs.next()) {
            String name = rs.getString(foodName);
            String type = rs.getString(foodType);
            String isExotic = rs.getString(exotic);
            //Вывести результат запроса
            String output = "Товар №%d: %s - %s - %s";
            System.out.println(String.format(output, ++count,  name, type, isExotic));
        }
    }


    @Дано("Добавление новых товаров и вывод списка добавленных товаров по параметрам {string}, {string},{string}")
    public void добавлениеНовыхТоваровИВыводСпискаДобавленныхТоваровПоПараметрам(String arg0, String arg1, String arg2) throws SQLException {
        String insert = "insert into food ( food_name, food_type, food_exotic) values ( ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(insert);

        // Добавить данные
        Object[][] data = {
                {"Морковь", "Vegetable", false},
                {"Слива", "Fruit", false},

        };
        //  batches параметры
        for (Object[] row : data) {
            ps.setString(1, (String) row[0]); // name
            ps.setString(2, (String) row[1]); // type
            ps.setBoolean(3, (Boolean) row[2]); // exotic
            ps.addBatch();
        }
        int[] batchResults = ps.executeBatch();
        System.out.println("Товары успешно добавлены");
        // Проверить, что добавились нужные товары с нужными значениями
        String sql = "SELECT * FROM food order by food_id desc limit 2";
        //PreparedStatement для параметризованного SQL-запроса
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        //Проверить, что в таблице появился новый товар с необходимыми параметрами
        int count = 0;
        while (resultSet.next()) {
            String name = resultSet.getString(arg0);
            String type = resultSet.getString(arg1);
            int booleanExotic = resultSet.getInt(arg2);
            String output = "Товар №%d: %s - %s - %s";
            System.out.println(String.format(output, ++count, name, type, booleanExotic));
        }
    }

    @Затем("Удалить добавленный товар {string} из таблицы и проверить, что {string}")
    @Step("Удалить добавленный товар Морковь из таблицы")
    public void удалитьДобавленныеТоварыИИзТаблицы(String arg0,String arg1) throws SQLException {

        String sql = "DELETE FROM food WHERE food_name=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, arg0);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println(arg1);
        }
    }

    @Затем("Удалить  товар {string} из таблицы и проверить, что {string}")
    @Step("Удалить добавленный товар Слива из таблицы")
    public void удалитьТоварИзТаблицы(String arg0,String arg1) throws SQLException {

        String sql = "DELETE FROM food WHERE food_name=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, arg0);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println(arg1);
        }
    }

    @Дано("Удалить добавленный товар {string} с значениями {string} и {string}, проверить,что товар удален")
    @Step("Удалить добавленный товар Манго, проверить,что товар удален")
    public void удалитьДобавленныйТоварСЗначениямиИПроверитьЧтоТоварУдален(String arg0, String arg1, String arg2) throws SQLException {
        String sqlDel = "DELETE FROM food WHERE food_name=?";
        PreparedStatement statm = connection.prepareStatement(sqlDel);
        statm.setString(1, arg0);
        statm.executeUpdate();
        String sqlSel = "SELECT * FROM food";
        PreparedStatement pstamt = connection.prepareStatement(sqlSel);
        ResultSet rsDel = pstamt.executeQuery();
        //Проверить,что товар удалился
        rsDel.last();
        Assertions.assertFalse(arg0.equals(rsDel.getString("food_name"))
                        && (arg1.equals(rsDel.getString("food_type")))
                        && (Boolean.valueOf(arg2).equals(rsDel.getBoolean("food_exotic"))),
                "Товар не удалился");
    }

    @Дано("Добавление нового товара Манго, проверить, что товар добавился:")
    @Step ("Добавление нового товара Манго, проверить, что товар добавился")
    public void добавлениеНовогоТовараМангоПроверитьЧтоТоварДобавился(Map<String,String> prodValue) throws SQLException {
        String sql = "insert into food (food_name, food_type, food_exotic) values ( ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, prodValue.get("food_name"));
        pstmt.setString(2, prodValue.get("food_type"));
        pstmt.setBoolean(3, Boolean.parseBoolean(prodValue.get("food_exotic")));
        pstmt.executeUpdate();
        //Проверить,что товар добавился
        String sqlSel = "SELECT * FROM food";
        PreparedStatement pstamt = connection.prepareStatement(sqlSel);
        ResultSet rs = pstamt.executeQuery();
        rs.last();
        Assertions.assertAll(" В таблице присутствует товар с значениями Манго, Fruit, true",
                () -> assertEquals(rs.getString("food_name"), "Манго"),
                () -> assertEquals(rs.getString("food_type"), "Fruit"),
                () -> assertEquals(rs.getBoolean("food_exotic"), true)
        );

    }
    }






