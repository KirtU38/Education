import bot.AvitoCallable;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static HashMap<String, String> mapOfCities = fillListWithCities();
    // Если товары на 50% дешевле средней стоимости, то они не учитываются
    public static final int PRICE_THRESHOLD = 50;
    // Сколько страниц считать
    public static final int PAGES_TO_CHECK = 5;
    public static int avaliableProcessors = Runtime.getRuntime().availableProcessors();
    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(avaliableProcessors);

    public static void main(String[] args) throws Exception {

        while (true) {
            // Получить инпут
            System.out.print("Введите город: ");
            String city = SCANNER.nextLine().trim().toLowerCase(Locale.ROOT).replaceAll(" ", "-");
            System.out.print("Что ищем: ");
            String product = SCANNER.nextLine().trim().toLowerCase(Locale.ROOT);

            // Если этот город есть в списке, тогда продолжаем
            if (mapOfCities.containsKey(city)) {
                String cityInEnglish = mapOfCities.get(city);
                String URL = "https://www.avito.ru/" + cityInEnglish;

                // Подключаемся к сайту по нужным параметрам и получаем HTML документ
                // Если статус 404, значит ни одного товара не найдено
                Document htmlDoc = getHtmlDoc(product, URL);
                if (htmlDoc == null) continue;

                // Находим количество страниц
                Elements pages = htmlDoc.select("span[data-marker~=page[(]\\d+[)]]");

                // Находим стреднюю цену по всем страницам(по дефолту 5 страниц)
                findAveragePrice(product, cityInEnglish, pages);
            } else {
                System.out.println("Нет такого города");
            }
        }
    }

    private static Document getHtmlDoc(String product, String URL) throws IOException {
        Document htmlDoc = null;
        try {
            htmlDoc = Jsoup.connect(URL).data("q", product).get();
        } catch (HttpStatusException e) {
            System.out.println("Такого товара не найдено в вашем городе");
            return null;
        }
        return htmlDoc;
    }

    // Если там больше одной страницы, то выполняем в многопоточке
    private static void findAveragePrice(String product, String cityInEnglish, Elements pages) throws InterruptedException {

        if (pages.size() > 0) {
            System.out.println("Многопоток");
            String lastPage = pages.get(pages.size() - 1).text();
            Double averagePriceResult = calculateAverageOnAllPages(lastPage, cityInEnglish, product);
            System.out.println(averagePriceResult);
        } else {
            System.out.println("Один");
            List<Double> listOfPrices = new AvitoCallable("1", cityInEnglish, product).call();
            double averagePriceResult = calculateAveragePriceFromList(listOfPrices);
            System.out.println(averagePriceResult);
        }
    }

    private static void getAveragePriceOnPage(String city, String product, Document htmlDoc) {

        Elements elementsInYourCity = htmlDoc.select("div[data-marker=catalog-serp]");
        Elements elementsPrices = elementsInYourCity.select("span[class~=price-text-.+]");

        // Если есть предложения именно в твоём городе
        if (elementsPrices.size() > 0) {
            Elements elementsInOtherCities = htmlDoc.select("div[class~=items-extra.+]");

            // Парсим документ в Лист цен
            List<Integer> listOfPrices = elementsPrices
                    .stream()
                    .filter(e -> e.text().matches("\\d+.+"))
                    .map(e -> Integer.parseInt(e.text().replaceAll("\\W", "")))
                    .collect(Collectors.toList());

            // Проходимся по всем элементам и находим ОБЩУЮ Среднюю цену
            double averagePriceCommon = listOfPrices
                    .stream()
                    .mapToInt(p -> p)
                    .average()
                    .getAsDouble();

            // Переменные ОБЩЕЙ средней цены, и минимальной цены которая равна 50% от общей, такие не будут учитываться
            double deletionThreshold = (averagePriceCommon / 100) * PRICE_THRESHOLD;

            // Удаляем те, которые слишком дешевые
            // Если цена в два раза меньше средней, мы не считаем её
            double realAveragePrice = listOfPrices
                    .stream()
                    .filter(p -> (averagePriceCommon - p) < deletionThreshold)
                    .mapToDouble(p -> p)
                    .average()
                    .getAsDouble();

            System.out.println(realAveragePrice != 0
                    ? String.format("Средняя цена %s в %s = %,.0f ₽\n", product, city, realAveragePrice)
                    : "");
        } else {
            System.out.println("К сожалению в твоём городе не продают " + product);
        }
    }

    public static Double calculateAverageOnAllPages(String lastPage, String cityInEnglish, String product) throws InterruptedException {

        List<Future<List<Double>>> listOfTasks = new ArrayList<>();
        List<List<Double>> listOfResultsOnEveryPage = new ArrayList<>();
        int lastPageInt = Integer.parseInt(lastPage);

        // Ограничиваем сколько страниц он будет считать
        if (lastPageInt > Main.PAGES_TO_CHECK) {
            lastPageInt = Main.PAGES_TO_CHECK;
        }

        for (int i = 1; i <= lastPageInt; i++) {
            AvitoCallable task = new AvitoCallable(String.valueOf(i), cityInEnglish, product);
            Future<List<Double>> future = Main.EXECUTOR_SERVICE.submit(task);
            listOfTasks.add(future);
            Thread.sleep(400);
        }
        listOfTasks.forEach(future -> {
            try {
                listOfResultsOnEveryPage.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        // Из всех Листов цен получить Лист<Double> и получим ОБЩУЮ среднюю цену
        List<Double> listOfPrices = listOfResultsOnEveryPage
                .stream()
                .filter(list -> list != null)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return calculateAveragePriceFromList(listOfPrices);
    }

    public static double calculateAveragePriceFromList(List<Double> listOfPrices) {

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

    private static HashMap<String, String> fillListWithCities() {

        HashMap<String, String> map = new HashMap<String, String>() {{
            put("алушта", "alushta");
            put("феодосия", "feodosiya");
            put("ялта", "yalta");
            put("севастополь", "sevastopol");
            put("симферополь", "simferopol");
            put("абакан", "abakan");
            put("анапа", "anapa");
            put("ангарск", "angarsk");
            put("архангельск", "arhangelsk");
            put("астрахань", "astrahan");
            put("барнаул", "barnaul");
            put("белгород", "belgorod");
            put("благовещенск", "amurskaya_oblast_blagoveschensk");
            put("чебоксары", "cheboksary");
            put("челябинск", "chelyabinsk");
            put("череповец", "cherepovets");
            put("черняховск", "chernyahovsk");
            put("чита", "chita");
            put("екатеринбург", "ekaterinburg");
            put("геленджик", "gelendzhik");
            put("иркутск", "irkutsk");
            put("ижевск", "izhevsk");
            put("кабардинка", "kabardinka");
            put("калининград", "kaliningrad");
            put("казань", "kazan");
            put("кемерово", "kemerovo");
            put("хабаровск", "habarovsk");
            put("ханты-Мансийск", "hanty-mansiysk");
            put("кисловодск", "kislovodsk");
            put("комсомольск-на-Амуре", "komsomolsk-na-amure");
            put("кострома", "kostroma");
            put("краснодар", "krasnodar");
            put("красноярск", "krasnoyarsk");
            put("курган", "kurgan");
            put("курск", "kursk");
            put("липецк", "lipetsk");
            put("магадан", "magadan");
            put("магнитогорск", "magnitogorsk");
            put("махачкала", "mahachkala");
            put("минеральные Воды", "mineralnye_vody");
            put("москва", "moskva");
            put("мурманск", "murmansk");
            put("находка", "nahodka");
            put("нальчик", "nalchik");
            put("нижневартовск", "nizhnevartovsk");
            put("нижний Новгород", "nizhniy_novgorod");
            put("ноябрьск", "noyabrsk");
            put("норильск", "norilsk");
            //////////////////////////////////
            put("ростов-на-дону", "rostov-na-donu");
            put("ханты-мансийск", "hanty-mansiysk");
            put("майма", "mayma");
            put("сант-питербург", "sankt-peterburg");
            put("сант-петербург", "sankt-peterburg");
            put("санкт-питербург", "sankt-peterburg");
            put("санкт-петербург", "sankt-peterburg");
            put("питер", "sankt-peterburg");
            put("новосибирск", "novosibirsk");
            put("новосиб", "novosibirsk");
            put("сиб", "novosibirsk");
        }};
        return map;
    }
}