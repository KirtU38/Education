package bot;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class AvitoCallable implements Callable<List<Double>> {

    String page;
    String cityInEnglish;
    String product;

    public AvitoCallable(String page, String cityInEnglish, String product) {
        this.page = page;
        this.cityInEnglish = cityInEnglish;
        this.product = product;
    }

    @Override
    public List<Double> call() {

        System.out.println(page + "   " + Thread.currentThread().getName());
        List<Double> listOfPrices = null;
        int sizeOfPrices = 0;
        try {
            String URL = "https://www.avito.ru/" + cityInEnglish;
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
        System.out.println(sizeOfPrices + " товаров "+ Thread.currentThread().getName());
        return listOfPrices;
    }
}
