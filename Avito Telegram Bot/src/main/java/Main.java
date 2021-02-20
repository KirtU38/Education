import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static HashMap<String, String> mapOfCities = fillListWithCities();
    // Если товары на 50% дешевле средней стоимости, то они не учитываются
    private static final int PRICE_THRESHOLD = 30;

    public static void main(String[] args) throws IOException {

        while (true) {
            System.out.print("Введите город: ");
            // String city = "sankt-peterburg";
            String city = SCANNER.nextLine().trim().toLowerCase(Locale.ROOT).replaceAll(" ", "-");
            System.out.print("Что ищем: ");
            // String product = "iphone 6";
            String product = SCANNER.nextLine().trim();

            if (mapOfCities.containsKey(city)) {
                // String cityConverted = "sankt-peterburg";
                String cityConverted = mapOfCities.get(city);

                String URL = "https://www.avito.ru/" + cityConverted;

                // Подключаемся к сайту и получаем HTML документ и выбираем только <span> с ценами
                Document htmlDoc = Jsoup.connect(URL).data("q", product).get();
                Elements elements = htmlDoc.select("span[class~=price-text-.+]");

                // Пишем в файл, outerHtml() чтобы перевести Document в String
                // PrintWriter writer = new PrintWriter("src/main/resources/Avito.html");
                // writer.write(elements.outerHtml());
                // writer.flush();
                // writer.close();

                // Извлекаем из документа только числа в Лист
                List<Integer> listOfPricesInt = elements
                        .stream()
                        .filter(e -> e.text().matches("\\d+.+"))
                        .map(e -> Integer.parseInt(e.text().replaceAll("\\W", "")))
                        .collect(Collectors.toList());

                // Проходимся по всем элементам и находим ОБЩУЮ Среднюю цену
                OptionalDouble averagePriceOptional = listOfPricesInt
                        .stream()
                        .mapToInt(e -> e)
                        .average();

                // Переменные ОБЩЕЙ средней цены, и минимальной цены которая равна 50% от общей, такие не будут учитываться
                double averagePrice = averagePriceOptional.getAsDouble();
                double deletionThreshold = (averagePrice / 100) * PRICE_THRESHOLD;

                // Удаляем те, которые слишком дешевые
                // Если цена в два раза меньше средней, не считаем её
                double realAveragePrice = listOfPricesInt
                        .stream()
                        .filter(e -> (averagePrice - e) < deletionThreshold)
                        .mapToDouble(e -> e)
                        .average()
                        .getAsDouble();

                System.out.println(realAveragePrice != 0
                        ? String.format("Средняя цена %s в %s = %,.0f ₽\n", product, city, realAveragePrice)
                        : "");
            } else {
                System.out.println("Нет такого города");
            }
        }
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
            put("", "");
            put("", "");
            put("", "");
            put("сант-питербург", "sankt-peterburg");
            put("сант-петербург", "sankt-peterburg");
            put("санкт-питербург", "sankt-peterburg");
            put("санкт-петербург", "sankt-peterburg");
            put("питер", "sankt-peterburg");
        }};
        return map;
    }
}
