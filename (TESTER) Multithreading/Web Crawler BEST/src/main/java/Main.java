import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static final String ROOT_URL = "https://skillbox.ru/";
    public static final HashSet<String> setOfUniqueLinks = new HashSet<>();

    public static void main(String[] args) throws IOException, InterruptedException {

        PrintWriter writer = new PrintWriter("src/skillbox.txt");
        int numOfTabs = 0;
        exploreAndWriteURL(ROOT_URL, ROOT_URL, true, numOfTabs, writer);
    }

    /*Программа входит в URL и сначала проходит по всем относительным ссылкам,
    чтобы четко и последовательно создать карту сайта в глубину,
    для этого на 51 строке есть проверка if(isAbsolute)
    Выбрал подход писать в файл сразу, по ходу выполнения программы, вместо того, чтобы просто собрать
    массив всех ссылок а потом отдельно парсить весь массив*/

    public static void exploreAndWriteURL(String url,
                                          String rootURL,
                                          boolean isAbsolute,
                                          int numOfTabs,
                                          PrintWriter writer)
            throws IOException, InterruptedException {

        numOfTabs++;
        setOfUniqueLinks.add(url);
        Document doc = Jsoup.connect(url).maxBodySize(0).get();
        Elements elements = doc.select("a[href]");

        // Добавляем табы и записываем в файл
        writeURLToFile(url, numOfTabs, writer);
        System.out.println(">>> " + url + " <<<");

        // Заполняем массив относительными ссылками и сортируем по глубине ссылки
        ArrayList<String> listOfRelativeLinks = new ArrayList<>();
        fillArrayWithChildLinks(listOfRelativeLinks, elements, url, rootURL);

        // Пройти по относительным ссылкам
        goThroughLinks(listOfRelativeLinks, numOfTabs, rootURL, writer);

        if (isAbsolute) {
            // Заполняем массив абсолютными ссылками
            ArrayList<String> listOfAbsoluteLinks = new ArrayList<>();
            fillArrayWithAbsoluteLinks(rootURL, elements, listOfAbsoluteLinks);

            // Пройти по абсолютным
            goThroughLinks(listOfAbsoluteLinks, numOfTabs, rootURL, writer);
        }
    }

    private static void goThroughLinks(ArrayList<String> listOfLinks, int numOfTabs, String rootURL, PrintWriter writer)
            throws InterruptedException, IOException {

        if (!listOfLinks.isEmpty()) {
            for (String link : listOfLinks) {
                if (!setOfUniqueLinks.contains(link)) {
                    Thread.sleep(300);
                    exploreAndWriteURL(link, rootURL, false, numOfTabs, writer);
                }
            }
        }
    }

    private static void fillArrayWithAbsoluteLinks(
            String rootURL, Elements elements, ArrayList<String> setOfAbsoluteLinks) {

        for (Element e : elements) {
            String link = e.attr("href");
            if (isChildLink(link, rootURL) && isLink(link) && !setOfUniqueLinks.contains(link)) {
                setOfAbsoluteLinks.add(link);
            }
        }
    }


    private static void fillArrayWithChildLinks(
            ArrayList<String> listOfRelativeLinks, Elements elements, String url, String rootURL) {

        for (Element element : elements) {
            String rawLink = element.attr("href");
            if (isRelative(rawLink)) {
                String linkFormatted = rootURL.substring(0, rootURL.length() - 1) + rawLink;
                if (isChildLink(linkFormatted, url)
                        && isLink(linkFormatted) && !setOfUniqueLinks.contains(linkFormatted)) {
                    listOfRelativeLinks.add(linkFormatted);
                }
            }
        }
        listOfRelativeLinks.sort((o1, o2) -> {
            int l1 = o1.split("/").length;
            int l2 = o2.split("/").length;
            return Integer.compare(l1, l2);
        });
    }

    private static boolean isChildLink(String link, String root) {
        return link.matches(root + ".+");
    }

    private static void writeURLToFile(String url, int numOfTabs, PrintWriter writer) {
        StringBuilder tabs = new StringBuilder();
        for (int i = 0; i < numOfTabs; i++) {
            tabs.append("\t");
        }
        writer.write(tabs + url + "\n");
        writer.flush();
    }

    private static boolean isLink(String url) {
        return !url.matches(".+\\.pdf$")
                && !url.matches(".+\\.jar$")
                && !url.matches(".+\\.xml$")
                && !url.matches(".+\\?.+")
                && !url.matches(".+#.+");
    }

    private static boolean isRelative(String href) {
        if (href.matches("^/.+") && !href.matches("^//.+")) {
            return true;
        } else {
            return false;
        }
    }
}



