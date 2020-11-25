import java.io.IOException;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static final String PATH = "src/main/resources/skillbox.txt";
    public static final String ROOT_URL = "https://skillbox.ru/";
    public static final int NUM_OF_CORES = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws IOException, InterruptedException {

        int numOfTabs = 0;

        ForkJoinPool pool = new ForkJoinPool(NUM_OF_CORES);
        WebTask webTask = new WebTask(ROOT_URL, ROOT_URL, true, numOfTabs);
        List<String> listOfLinks = pool.invoke(webTask);
        listOfLinks.forEach(System.out::println);
    } // 439
}



