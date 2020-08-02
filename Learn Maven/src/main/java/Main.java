import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    private static final String imagesFolder = "images/";
    private static final String siteName = "https://lenta.ru/";

    public static void main(String[] args) {
        try {

            Document doc = Jsoup.connect(siteName).get();
            Elements elements = doc.select("img");

            elements.forEach(e -> {
                try {

                    String imgUrl = e.attr("abs:src");
                    String[] urlSplit = imgUrl.split("/");
                    String imgName = urlSplit[urlSplit.length - 1];


                    URL url = new URL(imgUrl);
                    String destName = imagesFolder + imgName;

                    InputStream inStream = url.openStream();
                    OutputStream outStream = new FileOutputStream(destName);

                    byte[] buffer = new byte[2048];

                    int length;

                    while ((length = inStream.read(buffer)) != -1) { // .read() возвращает кол-во прочитанных байтов
                        outStream.write(buffer, 0, length);
                        System.out.println(length);
                    }
                    inStream.close();
                    outStream.close();

                    System.out.println("Фото: " + imgName + " - успешно сохранено");





                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
