public class Saver implements Runnable {

    String insertQuery;
    DBConnection dbConnection;

    public Saver(String insertQuery, DBConnection dbConnection) {
        this.insertQuery = insertQuery;
        this.dbConnection = dbConnection;
    }

    @Override
    public void run() {
        try {
            dbConnection.multiInsert(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
