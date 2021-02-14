import java.sql.*;

public class DBConnection {

    private static Connection connection;

    private static final String dbName = "learn";
    private static final String dbUser = "root";
    private static final String dbPass = "password";

    public void multiInsert(String insertQuery) {
        try {
            System.out.println("Multi-insert");

            String sql = "INSERT INTO voter_count(name, birthDate) " +
                    "VALUES " + insertQuery;

            DBConnection.getConnection().createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, COUNT(*) as 'voteCount' \n" +
                "FROM learn.voter_count \n" +
                "GROUP BY name, birthDate\n" +
                "HAVING COUNT(*) > 1\n" +
                "ORDER BY voteCount DESC";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("voteCount"));
        }
        rs.close();
    }

    private static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName +
                                "?user=" + dbUser + "&password=" + dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "PRIMARY KEY(id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
