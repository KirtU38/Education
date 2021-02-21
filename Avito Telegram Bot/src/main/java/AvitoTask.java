import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class AvitoTask extends RecursiveTask<List<Double>> {

    String page;
    String cityInEnglish;
    String product;

    public AvitoTask(String page, String cityInEnglish, String product) {
        this.page = page;
        this.cityInEnglish = cityInEnglish;
        this.product = product;
    }

    public static Double calculateAverageOnAllPages(String lastPage, String cityInEnglish, String product) throws InterruptedException {

        List<RecursiveTask<List<Double>>> listOfTasks = new ArrayList<>();
        List<List<Double>> listOfResultsOnEveryPage = new ArrayList<>();

        int lastPageInt = Integer.parseInt(lastPage);

        // Ограничиваем сколько страниц он будет считать
        if (lastPageInt > Main.PAGES_TO_CHECK){
            lastPageInt = Main.PAGES_TO_CHECK;
        }

        for (int i = 1; i <= lastPageInt; i++) {
            RecursiveTask<List<Double>> task = new AvitoTask(String.valueOf(i), cityInEnglish, product);
            Thread.sleep(500);
            task.fork();
            listOfTasks.add(task);
        }
        listOfTasks.forEach(task -> listOfResultsOnEveryPage.add(task.join()));

        // Из всех Листов цен получить Лист<Double> и получим ОБЩУЮ среднюю цену
        List<Double> listOfPrices = listOfResultsOnEveryPage
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        // Считаем ОБЩЕЮ среднюю цену, учитывая слишком дешевые товары
        double averagePriceCommon = listOfPrices
                .stream()
                .mapToDouble(e -> e)
                .average()
                .getAsDouble();

        // А теперь удаляем цены, который слишком низкие и высчитываем РЕАЛЬНУЮ среднюю цену
        double deletionThreshold = (averagePriceCommon / 100) * Main.PRICE_THRESHOLD;
        double realAveragePrice = listOfPrices
                .stream()
                .filter(p -> (averagePriceCommon - p) < deletionThreshold)
                .mapToDouble(p -> p)
                .average()
                .getAsDouble();

        return realAveragePrice;
    }

    @Override
    protected List<Double> compute() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(page + "   " + Thread.currentThread().getName());
        List<Double> listOfPrices = null;
        try {
            String URL = "https://www.avito.ru/" + cityInEnglish;
            Document htmlDoc = Jsoup.connect(URL).data("p", page, "q", product).get();
            Elements elementsInYourCity = htmlDoc.select("div[data-marker=catalog-serp]");
            Elements elementsPrices = elementsInYourCity.select("span[class~=price-text-.+]");

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
        return listOfPrices;
    }
}
