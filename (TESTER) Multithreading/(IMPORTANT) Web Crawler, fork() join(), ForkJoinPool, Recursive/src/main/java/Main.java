import java.io.FileNotFoundException;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static final String PATH = "src/main/resources/skillbox.txt";
    public static final String ROOT_URL = "https://skillbox.ru/";
    public static final int NUM_OF_CORES = Runtime.getRuntime().availableProcessors();
    public static final ForkJoinPool pool = new ForkJoinPool(NUM_OF_CORES);

    public static void main(String[] args) throws FileNotFoundException {

        WebCrawler webCrawler = new WebCrawler();
        webCrawler.writeMapOfURLToFile(PATH, ROOT_URL, pool);
    }
}



