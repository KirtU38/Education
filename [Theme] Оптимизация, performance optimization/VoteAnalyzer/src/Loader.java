import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loader {

    private static final String FILE_NAME = "res/data-1572M.xml";
    public static int SIZE = 20_000_000;
    public static ExecutorService service = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        // Создаем БД как обьект
        DBConnection dbConnection = new DBConnection();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        // Передаем обьект БД в handler
        XMLHandler handler = new XMLHandler(dbConnection);

        service.submit(() -> {
            try {
                parser.parse(new File(FILE_NAME), handler);
                dbConnection.printVoterCounts();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        /*Thread parserThread = new Thread(() -> {
            try {
                parser.parse(new File(FILE_NAME), handler);
                dbConnection.printVoterCounts();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });*/
        /*service.hashCode()
        parserThread.start();
        parserThread.join();*/
        System.out.println(System.currentTimeMillis() - start + " ms");
    }
}

// Парсер парсит 20_000_000 букв из документа
// Создает отдельный поток savingThread для выгрузки в БД
// Пока идёт выгрузка в БД, Парсер набирает новых юзеров
// Если Парсер закончил первым, он ждет пока savingThread умрет через метод savingThread.join()

// Нельза вызывать .start() 2 раза на один Thread, даже после того как он закончил

// someThread.join() означает "Текущий thread - подожди пока этот thread(someThread) умрёт"

// .join можно вызывать на thread который ещё не инициализирован, допустим как в этом проекте в XMLHandler`е,
// перед тем как заново начать загрузку в БД, основной thread ждет пока savingThread умрет

