import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class Main {

    private static final String rootURL = "https://skillbox.ru";
    public static final HashSet<LinkPOJO> setOfUniqueLinks = new HashSet<>();

    public static void main(String[] args) throws IOException, InterruptedException {

        exploreURL(rootURL);
    }

    public static void exploreURL(String rootURL) throws IOException, InterruptedException {
        Document doc = Jsoup.connect(rootURL).maxBodySize(0).get();
        Elements elements = doc.select("a[href]");

        // Относительные ссылки
        HashSet<LinkPOJO> setOfRelativeLinks = new HashSet<>();
        for (Element e : elements) {
            String href = e.attr("href");
            if (isRelative(href)) {
                LinkPOJO relativeLink = new LinkPOJO(rootURL + href);
                relativeLink.setRelative(true);
                setOfRelativeLinks.add(relativeLink);
            }
        }
        System.out.println("Относительные " + setOfRelativeLinks);

        // Абсолютные ссылки
        HashSet<LinkPOJO> setOfAbsoluteLinks = new HashSet<>();
        for (Element e : elements) {
            String href = e.attr("href");
            if (isAbsolute(href, rootURL)) {
                LinkPOJO absoluteLink = new LinkPOJO(href);
                absoluteLink.setAbsolute(true);
                setOfAbsoluteLinks.add(absoluteLink);
            }
        }
        System.out.println("Абсолютные " + setOfAbsoluteLinks);

        for (LinkPOJO link : setOfRelativeLinks) {
            System.out.println(link + " Из Мейна Relative");
            String url = link.getUrl();
            exploreURL(url, rootURL);
            Thread.sleep(1000);
        }

        for (LinkPOJO link : setOfAbsoluteLinks) {
            System.out.println(link + " Из мейна Absolute");
            String url = link.getUrl();
            exploreURL(url, rootURL);
            Thread.sleep(1000);
        }
    }

    public static void exploreURL(String url, String rootURL) throws IOException, InterruptedException {
        Document doc = Jsoup.connect(url).maxBodySize(0).get();
        Elements elements = doc.select("a[href]");

        System.out.println(">> " + url + " <<");
        setOfUniqueLinks.add(new LinkPOJO(url));

        // Дочерние ссылки
        HashSet<LinkPOJO> setOfChildLinks = new HashSet<>();
        for (Element e : elements) {
            String href = e.attr("href");
            if (isAbsolute(href, url)) {
                LinkPOJO childLink = new LinkPOJO(href);
                childLink.setAbsolute(true);
                setOfChildLinks.add(childLink);
            }
        }
        System.out.println("Дочерние" + setOfChildLinks);

        // Абсолютные ссылки
        HashSet<LinkPOJO> setOfAbsoluteLinks = new HashSet<>();
        for (Element e : elements) {
            String href = e.attr("href");
            if (isAbsolute(href, rootURL)) {
                LinkPOJO absoluteLink = new LinkPOJO(href);
                absoluteLink.setAbsolute(true);
                setOfAbsoluteLinks.add(absoluteLink);
            }
        }
        System.out.println("Абсолютные " + setOfAbsoluteLinks);

        for (LinkPOJO link : setOfChildLinks) {
            if (!setOfUniqueLinks.contains(link)) {
                Thread.sleep(1000);
                String newURL = link.getUrl();
                exploreURL(newURL, rootURL);
            } else {
                System.out.println(link + " УЖЕ РАССМОТРЕНА");
            }
        }

        for (LinkPOJO link : setOfAbsoluteLinks) {
            if (!setOfUniqueLinks.contains(link)) {
                Thread.sleep(1000);
                String newURL = link.getUrl();
                exploreURL(newURL, rootURL);
            } else {
                System.out.println(link + " УЖЕ РАССМОТРЕНА");
            }
        }
    }


    private static boolean isAbsolute(String href, String url) {
        return href.matches(url + ".+/$");
    }

    private static boolean isRelative(String href) {
        return href.matches("^/.+/$");
    }
}



