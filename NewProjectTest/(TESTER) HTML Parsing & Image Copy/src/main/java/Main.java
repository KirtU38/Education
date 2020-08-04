import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("https://lenta.ru/").get();
        Elements elements = doc.select("img");

        String imgUrl = elements.get(0).attr("abs:src");
        String[] urlSplit = imgUrl.split("/");
        String imgName = urlSplit[urlSplit.length - 1];

        URL url = new URL(imgUrl);
        String destName = "images/" + imgName;

        InputStream inStream = url.openStream();
        OutputStream outStream = new FileOutputStream(destName);

        byte[] buffer = new byte[2048]; // буфер

        int length; // создаем здесь тк по другому никак
        while ((length = inStream.read(buffer)) != -1) { // .read() возвращает кол-во прочитанных байтов
            outStream.write(buffer, 0, length);
        }
        inStream.close();
        outStream.close();

        System.out.println("Фото: " + imgName + " - успешно сохранено");
    }
}
