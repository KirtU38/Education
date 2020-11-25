import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static final String path = "src/main/resources/skillbox.txt";
    public static final String ROOT_URL = "https://skillbox.ru/";

    public static void main(String[] args) throws IOException, InterruptedException {

        int numOfTabs = 0;
        PrintWriter writer = new PrintWriter(path);

        WebCrawler webCrawler = new WebCrawler();
        webCrawler.exploreAndWriteURL(ROOT_URL, ROOT_URL, true, numOfTabs, writer);
        writer.flush();
        writer.close();
    }
}



