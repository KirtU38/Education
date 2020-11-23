import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    private static final String rootURL = "https://jsoup.org/";
    public static final HashSet<String> setOfUniqueLinks = new HashSet<>();
    static PrintWriter writer;

    static {
        try {
            writer = new PrintWriter("src/asdqwebg.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        exploreAndWriteURL(rootURL);
        writer.flush();
        writer.close();
    }

    public static void exploreAndWriteURL(String url) throws IOException, InterruptedException {
        Document doc = Jsoup.connect(url).maxBodySize(0).get();
        Elements elements = doc.select("a[href]");
        System.out.println(">>> " + url + " <<<");
        writer.write(url + "\n");
        writer.flush();

        // Относительные ссылки
        ArrayList<String> listOfRelativeLinks = new ArrayList<>();

        for (Element e : elements) {
            String rawLink = e.attr("href");
            if (isRelative(rawLink)) {
                String fullRelativeLink = rootURL.substring(0, rootURL.length() - 1) + rawLink;
                listOfRelativeLinks.add(fullRelativeLink);
                System.out.println(fullRelativeLink + " RELATIVE ");
            }
        }
        listOfRelativeLinks.forEach(System.out::println); // Готовые
        listOfRelativeLinks.sort((o1, o2) -> {
            int l1 = o1.split("/").length;
            int l2 = o2.split("/").length;
            return Integer.compare(l1, l2);
        });
        System.out.println("            SORTED");
        listOfRelativeLinks.forEach(System.out::println);


        // Пройти по относительным
        int numOfTabs = 0;
        for (String link : listOfRelativeLinks) {
            if (isLink(link) && !setOfUniqueLinks.contains(link)) {
                setOfUniqueLinks.add(link);
                Thread.sleep(300);
                diveIntoRelativeLink(link, numOfTabs);
            }
        }

        /*// Абсолютные ссылки
        HashSet<String> setOfAbsoluteLinks = getAbsoluteLinks(elements);

        // Пройти по абсолютным
        for (String link : setOfAbsoluteLinks) {
            if (isLink(link) && !setOfUniqueLinks.contains(link)) {
                setOfUniqueLinks.add(link);
                Thread.sleep(300);
                exploreAndWriteURL(link, writer);
            }
        }*/

    }

    private static void diveIntoRelativeLink(String fullRelativeLink, int numOfTabs) throws IOException, InterruptedException {
        Document doc = Jsoup.connect(fullRelativeLink).maxBodySize(0).get();
        Elements elements = doc.select("a[href]");
        numOfTabs++;

        // Добавляем табы и записываем в файл
        StringBuilder tabs = new StringBuilder();
        for (int i = 0; i < numOfTabs; i++) {
            tabs.append("\t");
        }
        writer.write(tabs + fullRelativeLink + "\n");
        writer.flush();
        System.out.println(">>> " + fullRelativeLink + " <<<");
        System.out.println("ПИШЕМ");

        ArrayList<String> listOfRelativeLinks = new ArrayList<>();

        for (Element element : elements) {
            String rawLink = element.attr("href");
            String linkFormatted = rootURL.substring(0, rootURL.length() - 1) + rawLink;
            if (linkFormatted.matches(fullRelativeLink + ".+")
                    && isLink(linkFormatted) && !setOfUniqueLinks.contains(linkFormatted)) {
                listOfRelativeLinks.add(linkFormatted);
            }

        }
        listOfRelativeLinks.sort((o1, o2) -> {
            int l1 = o1.split("/").length;
            int l2 = o2.split("/").length;
            return Integer.compare(l1, l2);
        });

        if(!listOfRelativeLinks.isEmpty()){
            for (String link : listOfRelativeLinks){
                setOfUniqueLinks.add(link);
                Thread.sleep(300);
                diveIntoRelativeLink(link, numOfTabs);
            }
        }
    }


    private static HashSet<String> getAbsoluteLinks(Elements elements) {
        HashSet<String> setOfAbsoluteLinks = new HashSet<>();
        for (Element e : elements) {
            String link = e.attr("href");
            if (isAbsolute(link, rootURL)) {
                setOfAbsoluteLinks.add(link);
                System.out.println(link + " ABSOLUTE");
            }
        }
        return setOfAbsoluteLinks;
    }

    private static HashSet<String> getRelativeLinks(Elements elements) {
        HashSet<String> setOfRelativeLinks = new HashSet<>();
        for (Element e : elements) {
            String link = e.attr("href");
            if (isRelative(link)) {
                String relativeLink = rootURL.substring(0, rootURL.length() - 1) + link;
                setOfRelativeLinks.add(relativeLink);
                System.out.println(relativeLink + " RELATIVE ");
            }
        }
        return setOfRelativeLinks;
    }

    private static boolean isLink(String url) {
        return !url.matches(".+\\.pdf$")
                && !url.matches(".+\\.jar$")
                && !url.matches(".+\\?.+")
                && !url.matches(".+#.+");
    }

    private static boolean isElementOfPage(LinkPOJO link) {
        return link.getUrl().matches(".+\\?.+");
    }

    private static boolean isAbsolute(String href, String root) {
        return href.matches(root + ".+");
    }

    private static boolean isRelative(String href) {
        if (href.matches("^/.+") && !href.matches("^//.+")) {
            return true;
        } else {
            return false;
        }
    }
}



