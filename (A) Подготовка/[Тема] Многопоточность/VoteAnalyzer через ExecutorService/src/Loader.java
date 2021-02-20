import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Loader {

    private static final String FILE_NAME = "res/data-1572M.xml";
    public static int SIZE = 20_000_000;
    public static final ExecutorService service = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        // Создаем БД как обьект
        DBConnection dbConnection = new DBConnection();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        // Передаем обьект БД в handler
        XMLHandler handler = new XMLHandler(dbConnection);

        Runnable parseTask = () -> {
            try {
                parser.parse(new File(FILE_NAME), handler);
                dbConnection.printVoterCounts();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Future<?> parseTaskFuture = service.submit(parseTask);
        parseTaskFuture.get();

        System.out.println(System.currentTimeMillis() - start + " ms");
    }
}

// Парсер парсит 20_000_000 букв из документа
// Создает отдельный таск для выгрузки в БД
// Пока идёт выгрузка в БД, Парсер набирает новых юзеров
// Если Парсер закончил первым, он ждет пока savingThread умрет через метод savingThread.join()

// Здесь мы используем future.get() как аналогию thread.join(), но проверяем чтобы future не был null

