import java.sql.*;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "password";

        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select course_name,\n" +
                    "ABS((COUNT(*)/ TIMESTAMPDIFF(MONTH ,'2018-12-31', min(subscription_date)))) as 'sales_per_month'\n" +
                    "from purchaselist\n" +
                    "where year(subscription_date) = 2018\n" +
                    "group by course_name\n" +
                    "order by sales_per_month DESC, course_name;\n");

            while (resultSet.next()) {
                String course_name = resultSet.getString("course_name");
                String sales_per_month = resultSet.getString("sales_per_month");

                System.out.println(course_name + " - " + sales_per_month);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
