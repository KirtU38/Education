import java.sql.*;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "password";


        try {

            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement statement = connection.createStatement();

            statement.execute("update students set name = 'Фуриков Эрнст' where id = 1;");

            ResultSet resultSet = statement.executeQuery("select course_name,\n" +
                    "ABS((COUNT(*)/ TIMESTAMPDIFF(MONTH ,max(subscription_date), min(subscription_date)))) as 'sales_per_month'\n" +
                    "from purchaselist\n" +
                    "where year(subscription_date) = 2018\n" +
                    "group by course_name\n" +
                    "order by sales_per_month DESC, course_name;");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("course_name") + " - " + resultSet.getString("sales_per_month"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
