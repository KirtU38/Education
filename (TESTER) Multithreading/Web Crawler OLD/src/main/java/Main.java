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
        exploreAndWriteURL(ROOT_URL, writer);
        writer.flush();
        writer.close();
    }

    public static void exploreAndWriteURL(String rootURL, PrintWriter writer)
            throws IOException, InterruptedException {

        setOfUniqueLinks.add(rootURL);
        Document doc = Jsoup.connect(rootURL).maxBodySize(0).get();
        Elements elements = doc.select("a[href]");

        System.out.println(">>> " + rootURL + " <<<");
        writer.write(rootURL + "\n");
        writer.flush();

        // Заполняем массив относительными ссылками и сортируем по глубине ссылки
        ArrayList<String> listOfRelativeLinks = new ArrayList<>();
        fillArrayWithRelativeLinks(listOfRelativeLinks, elements, rootURL);
        System.out.println(listOfRelativeLinks);

        // Пройти по относительным ссылкам
        int numOfTabs = 0;                                                 // Переменная для записи иерархии ссылок
        diveIntoRelativeLinks(listOfRelativeLinks, numOfTabs,rootURL, writer);

        // Заполняем массив абсолютными ссылками
        HashSet<String> setOfAbsoluteLinks = new HashSet<>();
        fillArrayWithAbsoluteLinks(rootURL, elements, setOfAbsoluteLinks);

        // Пройти по абсолютным
        diveIntoAbsoluteLinks(setOfAbsoluteLinks, numOfTabs, rootURL, writer);
    }

    private static void diveIntoAbsoluteLinks(HashSet<String> setOfAbsoluteLinks, int numOfTabs, String rootURL, PrintWriter writer)
            throws InterruptedException, IOException {

        if (!setOfAbsoluteLinks.isEmpty()) {
            for (String link : setOfAbsoluteLinks) {
                if (!setOfUniqueLinks.contains(link)) {
                    Thread.sleep(300);
                    diveIntoAbsoluteLink(link, numOfTabs, rootURL, writer);
                }
            }
        }
    }

    private static void diveIntoRelativeLink(String fullRelativeLink, int numOfTabs,String rootURL, PrintWriter writer)
            throws IOException, InterruptedException {

        numOfTabs++;
        setOfUniqueLinks.add(fullRelativeLink);
        Document doc = Jsoup.connect(fullRelativeLink).maxBodySize(0).get();
        Elements elements = doc.select("a[href]");

        // Добавляем табы и записываем в файл
        writeLinkToFile(fullRelativeLink, numOfTabs, writer);
        System.out.println(">>> " + fullRelativeLink + " <<<");

        // Заполняем массив дочерними ссылками и сортируем по глубине ссылки
        ArrayList<String> listOfRelativeLinks = new ArrayList<>();
        fillArrayWithChildLinks(listOfRelativeLinks, elements, fullRelativeLink, rootURL);

        // Заходим в каждую относительную ссылку
        diveIntoRelativeLinks(listOfRelativeLinks, numOfTabs,rootURL, writer);
    }

    private static void diveIntoRelativeLinks(ArrayList<String> listOfRelativeLinks, int numOfTabs,String rootURL, PrintWriter writer)
            throws InterruptedException, IOException {

        if (!listOfRelativeLinks.isEmpty()) {
            for (String link : listOfRelativeLinks) {
                if (!setOfUniqueLinks.contains(link)) {
                    Thread.sleep(300);
                    diveIntoRelativeLink(link, numOfTabs,rootURL, writer);
                }
            }
        }
    }

    private static void diveIntoAbsoluteLink(String absoluteLink, int numOfTabs, String rootURL, PrintWriter writer)
            throws IOException, InterruptedException {

        numOfTabs++;
        setOfUniqueLinks.add(absoluteLink);
        Document doc = Jsoup.connect(absoluteLink).maxBodySize(0).get();
        Elements elements = doc.select("a[href]");

        writeLinkToFile(absoluteLink, numOfTabs, writer);
        System.out.println(">>> " + absoluteLink + " ABS <<< ROOT >>>" + rootURL);

        ArrayList<String> listOfRelativeLinks = new ArrayList<>();
        fillArrayWithRelativeLinks(listOfRelativeLinks, elements, rootURL);

        diveIntoRelativeLinks(listOfRelativeLinks, numOfTabs,rootURL, writer);

        // Заполняем массив абсолютными ссылками
        HashSet<String> setOfAbsoluteLinks = new HashSet<>();
        fillArrayWithAbsoluteLinks(rootURL, elements, setOfAbsoluteLinks);

        // Пройти по абсолютным
        diveIntoAbsoluteLinks(setOfAbsoluteLinks, numOfTabs, rootURL, writer);
    }

    private static void fillArrayWithAbsoluteLinks(
            String rootURL, Elements elements, HashSet<String> setOfAbsoluteLinks) {

        for (Element e : elements) {
            String link = e.attr("href");
            if (isChildLink(link, rootURL) && isLink(link) && !setOfUniqueLinks.contains(link)) {
                setOfAbsoluteLinks.add(link);
            }
        }
    }

    private static void fillArrayWithRelativeLinks(
            ArrayList<String> listOfRelativeLinks, Elements elements, String rootURL) {

        for (Element e : elements) {
            String rawLink = e.attr("href");
            if (isRelative(rawLink)) {
                String fullRelativeLink = rootURL.substring(0, rootURL.length() - 1) + rawLink;
                if (isLink(fullRelativeLink) && !setOfUniqueLinks.contains(fullRelativeLink)) {
                    listOfRelativeLinks.add(fullRelativeLink);
                }
            }
        }
        listOfRelativeLinks.sort((o1, o2) -> {
            int l1 = o1.split("/").length;
            int l2 = o2.split("/").length;
            return Integer.compare(l1, l2);
        });
    }


    private static void fillArrayWithChildLinks(
            ArrayList<String> listOfRelativeLinks, Elements elements, String fullRelativeLink, String rootURL) {

        for (Element element : elements) {
            String rawLink = element.attr("href");
            String linkFormatted = rootURL.substring(0, rootURL.length() - 1) + rawLink;
            if (isChildLink(linkFormatted, fullRelativeLink)
                    && isLink(linkFormatted) && !setOfUniqueLinks.contains(linkFormatted)) {
                listOfRelativeLinks.add(linkFormatted);
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

    private static void writeLinkToFile(String fullRelativeLink, int numOfTabs, PrintWriter writer) {
        StringBuilder tabs = new StringBuilder();
        for (int i = 0; i < numOfTabs; i++) {
            tabs.append("\t");
        }
        writer.write(tabs + fullRelativeLink + "\n");
        writer.flush();
    }

    private static HashSet<String> getRelativeLinks(Elements elements) {
        HashSet<String> setOfRelativeLinks = new HashSet<>();
        for (Element e : elements) {
            String link = e.attr("href");
            if (isRelative(link)) {
                String relativeLink = ROOT_URL.substring(0, ROOT_URL.length() - 1) + link;
                setOfRelativeLinks.add(relativeLink);
                System.out.println(relativeLink + " RELATIVE ");
            }
        }
        return setOfRelativeLinks;
    }

    private static boolean isLink(String url) {
        return !url.matches(".+\\.pdf$")
                && !url.matches(".+\\.jar$")
                && !url.matches(".+\\.xml$")
                && !url.matches(".+\\?.+")
                && !url.matches(".+#.+");
    }

    private static boolean isAbsolute(String href, String root) {
        return isChildLink(href, root);
    }

    private static boolean isRelative(String href) {
        if (href.matches("^/.+") && !href.matches("^//.+")) {
            return true;
        } else {
            return false;
        }
    }
}



