import java.sql.*;

public class Main {

  public static void main(String[] args) {

    String url = "jdbc:mysql://localhost:3306/skillbox";
    String user = "root";
    String pass = "password";

    try {
      Connection connection = DriverManager.getConnection(url, user, pass);
      Statement statement = connection.createStatement();
      // PreparedStatement preparedStatement = connection.prepareStatement();
      ResultSet resultSet =
          statement.executeQuery(
              "SELECT course_name,\n"
                  + "ABS((COUNT(*)/ TIMESTAMPDIFF(MONTH ,MAX(subscription_date), MIN(subscription_date)))) AS 'sales_per_month'\n"
                  + "FROM Purchaselist\n"
                  + "WHERE YEAR(subscription_date) = 2018\n"
                  + "GROUP BY course_name\n"
                  + "ORDER BY sales_per_month DESC, course_name;\n");

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
