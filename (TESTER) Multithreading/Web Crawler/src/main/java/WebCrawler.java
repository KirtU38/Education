import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class WebCrawler {

    private static final HashSet<String> SET_OF_UNIQUE_LINKS = new HashSet<>();

    /*Программа входит в URL и сначала проходит по всем относительным ссылкам,
    чтобы четко и последовательно создать карту сайта в глубину,
    для этого на 44 строке есть проверка if(isAbsolute)
    Выбрал подход писать в файл сразу, по ходу выполнения программы, вместо того, чтобы просто собрать
    массив всех ссылок а потом отдельно парсить весь массив*/

    public void exploreAndWriteURL(String url,
                                          String rootURL,
                                          boolean isAbsolute,
                                          int numOfTabs,
                                          PrintWriter writer)
            throws IOException, InterruptedException {

        numOfTabs++;
        SET_OF_UNIQUE_LINKS.add(url);
        Document doc = Jsoup.connect(url).maxBodySize(0).get();
        Elements elements = doc.select("a[href]");

        // Записываем в файл с учетом накопленных табов
        writeURLToFile(url, numOfTabs, writer);
        System.out.println(">>> " + url + " <<< - " + (isAbsolute ? "ABSOLUTE" : "RELATIVE"));

        // Заполняем массив относительными ссылками и сортируем по глубине ссылки
        ArrayList<String> listOfRelativeLinks = new ArrayList<>();
        fillArrayWithChildLinks(listOfRelativeLinks, elements, url, rootURL);

        // Пройти по относительным ссылкам
        goThroughLinks(listOfRelativeLinks, numOfTabs, rootURL, writer, false);

        if (isAbsolute) {
            // Заполняем массив абсолютными ссылками
            ArrayList<String> listOfAbsoluteLinks = new ArrayList<>();
            fillArrayWithAbsoluteLinks(rootURL, elements, listOfAbsoluteLinks);

            // Пройти по абсолютным
            goThroughLinks(listOfAbsoluteLinks, numOfTabs, rootURL, writer, true);
        }
    }

    private void goThroughLinks(ArrayList<String> listOfLinks,
                                int numOfTabs, String rootURL, PrintWriter writer, boolean isAbsolute)
            throws InterruptedException, IOException {

        if (!listOfLinks.isEmpty()) {
            for (String link : listOfLinks) {
                if (!SET_OF_UNIQUE_LINKS.contains(link)) {
                    Thread.sleep(200);
                    exploreAndWriteURL(link, rootURL, isAbsolute, numOfTabs, writer);
                }
            }
        }
    }

    private void fillArrayWithAbsoluteLinks(
            String rootURL, Elements elements, ArrayList<String> setOfAbsoluteLinks) {

        for (Element e : elements) {
            String link = e.attr("href");
            if (isChildLink(link, rootURL) && isLink(link) && !SET_OF_UNIQUE_LINKS.contains(link)) {
                setOfAbsoluteLinks.add(link);
            }
        }
    }


    private void fillArrayWithChildLinks(ArrayList<String> listOfRelativeLinks,
                                         Elements elements, String url, String rootURL) {

        for (Element element : elements) {
            String rawLink = element.attr("href");
            if (isRelative(rawLink)) {
                String linkFormatted = rootURL.substring(0, rootURL.length() - 1) + rawLink;
                if (isChildLink(linkFormatted, url)
                        && isLink(linkFormatted) && !SET_OF_UNIQUE_LINKS.contains(linkFormatted)) {
                    listOfRelativeLinks.add(linkFormatted);
                }
            }
        }
        sortByDepth(listOfRelativeLinks);
    }

    private void sortByDepth(ArrayList<String> listOfRelativeLinks) {
        listOfRelativeLinks.sort((o1, o2) -> {
            int l1 = o1.split("/").length;
            int l2 = o2.split("/").length;
            return Integer.compare(l1, l2);
        });
    }

    private boolean isChildLink(String link, String root) {
        return link.matches(root + ".+");
    }

    private void writeURLToFile(String url, int numOfTabs, PrintWriter writer) {
        StringBuilder tabs = new StringBuilder();
        for (int i = 0; i < numOfTabs; i++) {
            tabs.append("\t");
        }
        writer.write(tabs + url + "\n");
        writer.flush();
    }

    private boolean isLink(String url) {
        return !url.matches(".+\\.pdf$")
                && !url.matches(".+\\.jar$")
                && !url.matches(".+\\.xml$")
                && !url.matches(".+\\?.+")
                && !url.matches(".+#.+");
    }

    private boolean isRelative(String link) {
        return link.matches("^/.+") && !link.matches("^//.+");
    }
}
