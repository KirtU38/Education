import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class WebCrawler extends RecursiveTask<List<String>> {

    private static final HashSet<String> SET_OF_UNIQUE_LINKS = new HashSet<>();

    private String url;
    private String rootURL;
    private boolean isAbsolute;
    private int numOfTabs;
    private final List<WebCrawler> listOfTasks = new ArrayList<>();
    private static final HashSet<String> setOfAbsoluteLinks = new HashSet<>();
    private List<String> listOfNestedLinks = new ArrayList<>();

    public WebCrawler() {}

    private WebCrawler(String url, String rootURL, boolean isAbsolute, int numOfTabs) {
        this.url = url;
        this.rootURL = rootURL;
        this.isAbsolute = isAbsolute;
        this.numOfTabs = numOfTabs;
    }

    public void writeMapOfURLToFile(String path, String rootURL, ForkJoinPool pool)
            throws FileNotFoundException {

        // Добавляем в лист только относительные ссылки и абсолютные с детьми
        listOfNestedLinks = pool.invoke(new WebCrawler(rootURL, rootURL, true, 0));

        // А после этого всей кучей добавляем абсолютные без детей в конец
        listOfNestedLinks.addAll(setOfAbsoluteLinks);

        // Записываем финальный лист в файл
        PrintWriter writer = new PrintWriter(path);
        listOfNestedLinks.forEach(writer::write);
    }

    @Override
    protected List<String> compute() {
        try {
            // Фильтрация пройденных ссылок
            if (SET_OF_UNIQUE_LINKS.contains(url)) {
                return listOfNestedLinks;
            }
            SET_OF_UNIQUE_LINKS.add(url);

            // Если на относительнной ссылке нашлась абсолютная с детьми(что проверяется дальше), то не добавляем таб,
            // так как выйдет неправильная логика
            if (!isAbsolute) {
                numOfTabs++;
            }

            Document doc = Jsoup.connect(url).maxBodySize(0).get();
            Elements elements = doc.select("a[href]");

            System.out.println(">>> " + url + " <<< - " + (isAbsolute ? "ABSOLUTE - " : "RELATIVE - ")
                    + Thread.currentThread().getName());

            // Заполняем массив относительными ссылками и сортируем по глубине ссылки
            ArrayList<String> listOfChildLinks = getChildLinks(elements);

            // Проверяем если это абсолютная ссылка без детей, то добавляем без табов в статичный сет
            // Если относительная или абсолютная с детьми, то добавляем с табами
            if (isAbsolute && listOfChildLinks.isEmpty()) {
                setOfAbsoluteLinks.add(url + "\n");
            } else {
                addURLToListWithTabs(listOfNestedLinks);
            }

            // Пройти по относительным ссылкам и раздать задачи
            goThroughLinksAndForkTasks(listOfChildLinks, false);
            System.out.println(listOfTasks.size() + " заданий у " + url + " - " + Thread.currentThread().getName());

            // Заполняем массив абсолютными ссылками
            ArrayList<String> listOfAbsoluteLinks = getAbsoluteLinks(elements);

            // Пройти по абсолютным
            goThroughLinksAndForkTasks(listOfAbsoluteLinks, true);

            // Дожидаемся выполнения всех задач и добавляем их результаты в лист
            // Результат: Относительные ссылки с детьми и абсолютные ссылки с детьми
            // Абсолютные без детей добавляются отдельно в конце метода writeMapOfURLToFile()
            for (WebCrawler task : listOfTasks) {
                listOfNestedLinks.addAll(task.join());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return listOfNestedLinks;
        }
        return listOfNestedLinks;
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

    private void addURLToListWithTabs(Collection<String> list) {
        StringBuilder tabs = new StringBuilder();
        tabs.append("\t".repeat(numOfTabs));
        list.add(tabs + url + "\n");
    }

    private void goThroughLinksAndForkTasks(ArrayList<String> listOfLinks, boolean isAbsolute) {
        try {
            if (!listOfLinks.isEmpty()) {
                for (String link : listOfLinks) {
                    if (!SET_OF_UNIQUE_LINKS.contains(link)) {
                        Thread.sleep(150);
                        WebCrawler task = new WebCrawler(link, rootURL, isAbsolute, numOfTabs);
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
