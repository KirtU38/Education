import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Loader {

    private static final String FILE_NAME = "res/data-18M.xml";

    public static void main(String[] args) throws Exception {

        // XMLHandlerBetter метод Xmx23M
        /*long memoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        SAXParserFactory factory1 = SAXParserFactory.newInstance();
        SAXParser parser1 = factory1.newSAXParser();
        XMLHandlerBest bestHandler = new XMLHandlerBest();
        parser1.parse(new File(FILE_NAME), bestHandler);
        bestHandler.printVoteStationsWorkTimes();
        bestHandler.printDuplicatedVoters();

        memoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - memoryUsage;
        System.out.println(memoryUsage / 1_000_000 + " mb memory usage\n");*/

        // XMLHandlerBetter метод Xmx23M
        long memoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        SAXParserFactory factory1 = SAXParserFactory.newInstance();
        SAXParser parser1 = factory1.newSAXParser();
        XMLHandlerBetter betterHandler = new XMLHandlerBetter();
        parser1.parse(new File(FILE_NAME), betterHandler);
        betterHandler.printVoteStationsWorkTimes();
        betterHandler.printDuplicatedVoters();

        memoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - memoryUsage;
        System.out.println(memoryUsage / 1_000_000 + " mb memory usage\n");

        // XMLHandler метод Xmx27M
        /*long memoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(FILE_NAME), handler);
        handler.printVoteStationsWorkTimes();
        handler.printDuplicatedVoters();

        memoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - memoryUsage;
        System.out.println(memoryUsage / 1_000_000 + " mb memory usage\n");*/

        // DOM метод Xmx190
        /*long memoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        DomParser domParser = new DomParser();
        domParser.parse(FILE_NAME);

        memoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - memoryUsage;
        System.out.println(memoryUsage / 1_000_000 + " mb memory usage\n");*/
    }
}