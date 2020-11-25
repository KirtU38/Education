import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class WebTask extends RecursiveTask<List<String>> {

    private static final HashSet<String> SET_OF_UNIQUE_LINKS = new HashSet<>();

    String url;
    String rootURL;
    boolean isAbsolute;
    int numOfTabs;
    List<WebTask> listOfRelTasks = new ArrayList<>();
    List<String> list = new ArrayList<>();

    public WebTask(String url, String rootURL, boolean isAbsolute, int numOfTabs) {
        this.url = url;
        this.rootURL = rootURL;
        this.isAbsolute = isAbsolute;
        this.numOfTabs = numOfTabs;
    }

    @Override
    protected List<String> compute() {

        try {

            if (SET_OF_UNIQUE_LINKS.contains(url)) { // Фильтрация пройденных ссылок
                return list;
            }

            SET_OF_UNIQUE_LINKS.add(url);

            List<WebTask> listOfAbsTasks = new ArrayList<>();
            if (!isAbsolute) {
                numOfTabs++;
            }

            // Записываем ссылку в лист с отступами
            addURLToListWithTabs(list);

            Document doc = getDocument();
            Elements elements = doc.select("a[href]");

            System.out.println(">>> " + url + " <<< - " + (isAbsolute ? "ABSOLUTE - " : "RELATIVE - ") + Thread.currentThread().getName());

            // Заполняем массив относительными ссылками и сортируем по глубине ссылки
            ArrayList<String> listOfRelativeLinks = getChildLinks(elements);

            // Пройти по относительным ссылкам и раздать задачи
            goThroughLinksAndForkTasks(listOfRelativeLinks, listOfRelTasks, false);

            if (isAbsolute) {
                // Заполняем массив абсолютными ссылками
                ArrayList<String> listOfAbsoluteLinks = getAbsoluteLinks(elements);

                // Пройти по абсолютным
                goThroughLinksAndForkTasks(listOfAbsoluteLinks, listOfRelTasks, true);

            }


            for (WebTask task : listOfRelTasks) { // Добавляем всё в большой лист
                list.addAll(task.join());
            }





        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }

        return list;
    }

    private ArrayList<String> getAbsoluteLinks(Elements elements) {
        ArrayList<String> listOfAbsoluteLinks = new ArrayList<>();
        for (Element e : elements) {
            String link = e.attr("href");
            if (isChildLink(link, rootURL) && isLink(link) && !SET_OF_UNIQUE_LINKS.contains(link)) {
                listOfAbsoluteLinks.add(link);
            }
        }
        return listOfAbsoluteLinks;
    }

    private ArrayList<String> getChildLinks(Elements elements) {
        ArrayList<String> listOfRelativeLinks = new ArrayList<>();

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
        return listOfRelativeLinks;
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


    private Document getDocument() {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    private void addURLToListWithTabs(List<String> list) {
        StringBuilder tabs = new StringBuilder();
        tabs.append("\t".repeat(numOfTabs));
        list.add(tabs + url + (isAbsolute ? " - ABSOLUTE" : " - RELATIVE"));
    }

    private void goThroughLinksAndForkTasks(ArrayList<String> listOfLinks, List<WebTask> listOfTasks, boolean isAbsolute) {
        try {
            if (!listOfLinks.isEmpty()) {
                for (String link : listOfLinks) {
                    if (!SET_OF_UNIQUE_LINKS.contains(link)) {
                        Thread.sleep(150);
                        WebTask task = new WebTask(link, rootURL, isAbsolute, numOfTabs);
                        task.fork();
                        listOfTasks.add(task);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
