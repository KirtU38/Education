import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class XMLHandler extends DefaultHandler {

    private Thread savingThread;
    private DBConnection dbConnection;
    public static StringBuilder insertQuery;
    private ExecutorService singleThread;

    public XMLHandler(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        insertQuery = new StringBuilder(Loader.SIZE);
        singleThread = Executors.newSingleThreadExecutor();
    }

    @Override
    public void startElement(String uri, String localName, String tag, Attributes attributes) {
        try {
            if (tag.equals("voter")) {
                String name = attributes.getValue("name");
                String birthDay = attributes.getValue("birthDay").replace('.', '-');

                insertQuery.append((insertQuery.length() == 0 ? "" : ",")
                        + "('" + name + "', '" + birthDay + "')");

                // Буфер Билдера, если size больше 20мил то сбрасывает всё отдельному потоку в БД и очищает Билдер
                if (insertQuery.length() > Loader.SIZE) {
                    System.out.println("Набралось в оригинал");
                    //savingThread.join();
                    singleThread.submit(() -> dbConnection.multiInsert(insertQuery.toString()));
                    //savingThread = new Thread(() -> dbConnection.multiInsert(insertQuery.toString()));
                    System.out.println("String передался в Multi-insert");
                    //savingThread.start();
                    insertQuery.delete(0, insertQuery.length());
                    System.out.println("Удалился оригинал");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endDocument() {
        try {
            if (insertQuery.length() > 0) {
                System.out.println("Сбрасываются последние элементы");
                savingThread.join();
                // Здесь для примера имплементация с Runnable, а не с лямбдой
                savingThread = new Thread(new Saver(insertQuery.toString(), dbConnection));
                System.out.println("String передался в Multi-insert");
                savingThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
