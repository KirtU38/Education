import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.RecursiveTask;

public class WebCrawlerVoid extends RecursiveTask<List<String>> {

    private static final HashSet<String> SET_OF_UNIQUE_LINKS = new HashSet<>();
    private static final HashSet<String> SET_OF_UNIQUE_LINKS1 = new HashSet<>();

    private String url;
    private String rootURL;
    private final List<WebCrawlerVoid> listOfTasks = new ArrayList<>();
    private final List<String> listOfFormatted = new ArrayList<>();
    private final List<String> finalList = new ArrayList<>();

    public WebCrawlerVoid() {
    }

    private WebCrawlerVoid(String url, String rootURL) {
        this.url = url;
        this.rootURL = rootURL;
    }

    public void writeMapOfURLToFile(String path, String rootURL)
            throws FileNotFoundException {

        WebCrawlerVoid task = new WebCrawlerVoid(rootURL, rootURL);
        List<String> list = task.invoke();
        sortByDepth(list);

        PrintWriter writer = new PrintWriter(path);


        // Format сразу во writer
        format(list.get(0), 0, list, writer);
        writer.flush();
        writer.close();

        // Format который return List
        /*List<String> strings = formatList(list.get(0), 0, list);
        strings.forEach(System.out::println);
        strings.forEach(link-> writer.write(link + "\n"));
        writer.flush();
        writer.close();*/


        // Format с ForkJoin
        /*List<String> strings = new FormatListForkJoin(list.get(0), 0, list).invoke();
        strings.forEach(System.out::println);
        strings.forEach(link-> writer.write(link + "\n"));
        writer.flush();
        writer.close();*/
    }

    private void format(String url, int numOfTabs, List<String> list, PrintWriter writer) {

        if (SET_OF_UNIQUE_LINKS1.contains(url)) {
            return;
        }
        writer.write("\t".repeat(numOfTabs) + url + "\n");
        SET_OF_UNIQUE_LINKS1.add(url);
        numOfTabs++;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).matches(url + ".+")
                    && !list.get(i).matches("^" + url + "$")) {
                format(list.get(i), numOfTabs, list, writer);
            }
        }
    }

    private List<String> formatList(String url, int numOfTabs, List<String> list) {

        List<String> finalList = new ArrayList<>();
        if (SET_OF_UNIQUE_LINKS1.contains(url)) {
            return finalList;
        }
        SET_OF_UNIQUE_LINKS1.add(url);
        finalList.add("\t".repeat(numOfTabs) + url);
        numOfTabs++;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).matches(url + ".+")
                    && !list.get(i).matches("^" + url + "$")) {
                finalList.addAll(formatList(list.get(i), numOfTabs, list));
            }
        }
        return finalList;
    }

    @Override
    protected List<String> compute() {
        try {
            finalList.add(url);

            Document doc = Jsoup.connect(url).maxBodySize(0).get();
            Elements elements = doc.select("a[href]");

            formatLinksAndAddToLists(elements); // Теперь все выглядят "skill.ru/code/" и раскидано по листам

            goThroughLinksAndForkTasks();

            System.out.println("<<< " + url);
        } catch (Exception e) {
            e.printStackTrace();
            return finalList;
        }
        return finalList;
    }

    private void formatLinksAndAddToLists(Elements elements) {

        for (Element element : elements) {
            String link = element.attr("href");
            if (isRelative(link)) { // "/code/"
                link = rootURL.substring(0, rootURL.length() - 1) + link; // "skillbox.ru/code/"
            }
            if (isChildLink(link, url) && isLink(link)
                    && !SET_OF_UNIQUE_LINKS.contains(link)) {

                SET_OF_UNIQUE_LINKS.add(link);
                listOfFormatted.add(link); // ["skillbox.ru/code/", "skillbox.ru/users/"]

            } else if (isChildLink(link, rootURL) && isLink(link)
                    && !SET_OF_UNIQUE_LINKS.contains(link)) {

                SET_OF_UNIQUE_LINKS.add(link);
                listOfFormatted.add(link);
            }
        }
    }

    private boolean isChildLink(String link, String root) {
        return link.matches(root + ".+");
    }

    private boolean isLink(String url) {
        return !url.matches(".+\\.pdf$")
                && !url.matches(".+\\.jar$")
                && !url.matches(".+\\.png$")
                && !url.matches(".+\\.xml$")
                && !url.matches(".+\\.xls$")
                && !url.matches(".+\\?.+")
                && !url.matches(".+#.+");
    }

    private boolean isRelative(String link) {
        return link.matches("^/.+") && !link.matches("^//.+");
    }

    private void goThroughLinksAndForkTasks() {
        try {
            for (String link : listOfFormatted) {
                Thread.sleep(150);
                WebCrawlerVoid task = new WebCrawlerVoid(link, rootURL);
                task.fork();
                listOfTasks.add(task);
            }
            listOfTasks.forEach(task -> finalList.addAll(task.join()));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sortByDepth(List<String> listOfLinks) {
        listOfLinks.sort((o1, o2) -> {

            int l1 = o1.split("/").length;
            int l2 = o2.split("/").length;
            if (l1 != l2) {
                return Integer.compare(l1, l2);
            } else {
                return o1.compareTo(o2);
            }
        });
    }
}
