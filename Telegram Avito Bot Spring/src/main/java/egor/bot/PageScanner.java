package egor.bot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class PageScanner implements Callable<List<Double>> {

    String page;
    String cityInEnglish;
    String product;

    public PageScanner(String page, String cityInEnglish, String product) {
        this.page = page;
        this.cityInEnglish = cityInEnglish;
        this.product = product;
    }

    @Override
    public List<Double> call() {


        List<Double> listOfPrices = null;
        int sizeOfPrices = 0;
        String URL;

        try {
            synchronized (MessageManager.class) {
                System.out.println(page + "   " + Thread.currentThread().getName());
                URL = "https://www.avito.ru/" + cityInEnglish;
                Thread.sleep(BotSettings.DELAY_BETWEEN_CONNECTIONS);
            }
            Document htmlDoc = Jsoup.connect(URL).data("p", page, "q", product).get();
            Elements elementsInYourCity = htmlDoc.select("div[data-marker=catalog-serp]");
            Elements elementsPrices = elementsInYourCity.select("span[class~=price-text-.+]");
            sizeOfPrices = elementsPrices.size();

            // Если есть предложения именно в твоём городе
            if (elementsPrices.size() > 0) {
                // Парсим документ в Лист цен
                listOfPrices = elementsPrices
                        .stream()
                        .filter(e -> e.text().matches("\\d+.+"))
                        .map(e -> Double.parseDouble(e.text().replaceAll("\\W", "")))
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Возвращаем Лист всех цен со страницы
        System.out.println(sizeOfPrices + " товаров " + Thread.currentThread().getName());
        return listOfPrices;
    }
}
