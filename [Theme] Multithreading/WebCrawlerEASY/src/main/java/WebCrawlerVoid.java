import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class WebCrawlerVoid extends RecursiveTask<List<String>> {

    private static final HashSet<String> SET_OF_UNIQUE_LINKS = new HashSet<>();

    private final String url;
    private final String rootURL;
    private final List<WebCrawlerVoid> listOfTasks = new ArrayList<>();
    private final List<String> finalList = new ArrayList<>();
    private final List<String> listOfFormatted = new ArrayList<>();
    int numOfTabs;

    public WebCrawlerVoid(String url, String rootURL, int numOfTabs) {
        this.url = url;
        this.rootURL = rootURL;
        this.numOfTabs = numOfTabs;
    }

    @Override
    protected List<String> compute() {
        try {
            System.out.println(url + " " + Thread.currentThread().getName());

            finalList.add("\t".repeat(numOfTabs) + url);
            numOfTabs++;

            Document doc = Jsoup.connect(url).maxBodySize(0).get();
            Elements elements = doc.select("a[href]");

            formatLinks(elements);

            listOfFormatted.forEach(link->{
                WebCrawlerVoid task = new WebCrawlerVoid(link, rootURL, numOfTabs);
                listOfTasks.add(task);
            });

            ForkJoinTask.invokeAll(listOfTasks)
                    .stream()
                    .map(ForkJoinTask::join)
                    .forEach(finalList::addAll);

        } catch (Exception e) {
            e.printStackTrace();
            return finalList;
        }
        return finalList;
    }

    private void formatLinks(Elements elements) {
        for (Element element : elements) {
            String link = element.attr("href");
            if (isRelative(link)) { // "/code/"
                link = rootURL.substring(0, rootURL.length() - 1) + link; // "skillbox.ru/code/"
            }
            if (link.matches(url + ".+") && !SET_OF_UNIQUE_LINKS.contains(link) && isLink(link)) {

                SET_OF_UNIQUE_LINKS.add(link);
                listOfFormatted.add(link);
            }
        }
        sortByDepth(listOfFormatted);
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
