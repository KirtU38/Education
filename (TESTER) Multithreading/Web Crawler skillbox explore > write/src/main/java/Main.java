import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static final String path = "src/main/resources/skillbox.txt";
    public static final String ROOT_URL = "https://skillbox.ru/";

    public static void main(String[] args) throws IOException, InterruptedException {

        WebCrawler webCrawler = new WebCrawler(ROOT_URL, ROOT_URL, true);

        ForkJoinPool pool = new ForkJoinPool(4);
        pool.invoke(webCrawler);

        System.out.println(WebCrawler.SET_OF_UNIQUE_LINKS.size());

        List<String> listOfLinks = new ArrayList<>(WebCrawler.SET_OF_UNIQUE_LINKS);

        listOfLinks.forEach(System.out::println);
    }
}



