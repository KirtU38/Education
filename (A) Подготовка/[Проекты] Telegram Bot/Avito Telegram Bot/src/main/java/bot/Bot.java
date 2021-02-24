package bot;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Bot extends TelegramLongPollingBot {

    private final String TOKEN = "1643407471:AAGlk8FB3HmCGnkgLq8iU5tjDxvFfFexfsg";
    private final String USER_NAME = "avito_price_bot";
    private final HashMap<String, String> mapOfCities = fillListWithCities();
    private final int PRICE_THRESHOLD = 50;
    private final int PAGES_TO_CHECK = 5;
    private final int avaliableProcessors = Runtime.getRuntime().availableProcessors();
    private final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(avaliableProcessors);

    @Override
    public String getBotUsername() {
        return USER_NAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        String text = update.getMessage().getText().toLowerCase(Locale.ROOT);
        String chatId = String.valueOf(update.getMessage().getChatId());
        SendMessage message = null;
        System.out.println(text);

        if (isMessage(update)) {
            System.out.println("asdadasd");
            if (text.equals("помощь")) {
                message = helpMessage(chatId);
            }
            if (text.equals("/start")) {
                System.out.println("GOOD");
                message = startMessage(chatId);
                System.out.println(message);
            }
            if (!text.equals("/start") && !text.equals("помощь")) {
                message = calculatedAveragePrice(update, chatId);
            }
            System.out.println("EXECUTE");
            execute(message);
        }
    }

    private SendMessage startMessage(String chatId) {
        SendMessage message;
        KeyboardButton button = new KeyboardButton();
        button.setText("Помощь");

        // Ряд
        KeyboardRow row = new KeyboardRow();
        row.add(button);
        List<KeyboardRow> listOfRows = new ArrayList<>();
        listOfRows.add(row);
        //
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(listOfRows);
        message = new SendMessage(chatId,
                "Привет! Чтобы узнать среднюю ценю любого товара на авито, просто введи Город, а потом Товар:\n" +
                        "Питер Samsung Galaxy S20");
        message.setReplyMarkup(replyKeyboardMarkup);
        return message;
    }

    private boolean isMessage(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }

    private SendMessage helpMessage(String chatId) {
        SendMessage message;
        //Кнопка
        KeyboardButton button = new KeyboardButton();
        button.setText("Помощь");

        // Ряд
        KeyboardRow row = new KeyboardRow();
        row.add(button);
        List<KeyboardRow> listOfRows = new ArrayList<>();
        listOfRows.add(row);
        //
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(listOfRows);
        message = new SendMessage();
        // SendMessage keyboard = new SendMessage();
        message.setChatId(chatId);
        message.setReplyMarkup(replyKeyboardMarkup);
        message.setText("Введите город, потом товар, например:\nПитер iphone 12 pro max");
        return message;
    }

    private SendMessage calculatedAveragePrice(Update update, String chatId) throws org.telegram.telegrambots.meta.exceptions.TelegramApiException, IOException, InterruptedException {
        SendMessage message;
        String userInput = update.getMessage().getText();
        String[] tokens = userInput.toLowerCase(Locale.ROOT).trim().split("\\s", 2);
        String city = tokens[0];
        String product = tokens[1];
        // String product = mapOfCities.containsKey(city) ? tokens[1] : "";

        if (mapOfCities.containsKey(city)) {
            message = new SendMessage(chatId,
                    "Считаю...");
            execute(message);
            String cityInEnglish = mapOfCities.get(city);
            String URL = "https://www.avito.ru/" + cityInEnglish + "?q=" + product;
            Document htmlDoc = Jsoup.connect(URL).get();

            if (htmlDoc != null) {
                Elements pages = htmlDoc.select("span[data-marker~=page[(]\\d+[)]]");
                Double averagePrice = findAveragePrice(product, cityInEnglish, pages);
                message = new SendMessage(chatId,
                        String.format("Средняя цена в городе %s = %,.0f ₽", city, averagePrice));
                message.setReplyMarkup(getInlineKeyboardMarkup(URL));
            } else {
                message = new SendMessage(chatId, "Такого товара нет в городе " + city);
            }
        } else {
            message = new SendMessage(chatId, "Введите город, потом товар");
        }
        return message;
    }

    private void executeHelpMessage(String chatId) throws org.telegram.telegrambots.meta.exceptions.TelegramApiException {
        //Кнопка
        KeyboardButton button = new KeyboardButton();
        button.setText("Помощь");

        // Ряд
        KeyboardRow row = new KeyboardRow();
        row.add(button);
        List<KeyboardRow> listOfRows = new ArrayList<>();
        listOfRows.add(row);
        //
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(listOfRows);
        SendMessage keyboard = new SendMessage();
        keyboard.setChatId(chatId);
        keyboard.setReplyMarkup(replyKeyboardMarkup);
        keyboard.setText("Введите город, потом товар, например:\nПитер iphone 12 pro max");
        execute(keyboard);
    }

    @Override
    public void onRegister() {
    }

    private Double findAveragePrice(String product, String cityInEnglish, Elements pages)
            throws InterruptedException {

        double averagePriceResult;

        if (pages.size() > 0) {
            System.out.println("Многопоток");
            String lastPage = pages.get(pages.size() - 1).text();
            averagePriceResult = calculateAverageOnAllPages(lastPage, cityInEnglish, product);
        } else {
            System.out.println("Один");
            List<Double> listOfPrices = new AvitoCallable("1", cityInEnglish, product).call();
            averagePriceResult = calculateAveragePriceFromList(listOfPrices);
        }
        return averagePriceResult;
    }

    private Double calculateAverageOnAllPages(String lastPage, String cityInEnglish, String product)
            throws InterruptedException {

        List<Future<List<Double>>> listOfTasks = new ArrayList<>();
        List<List<Double>> listOfResultsOnEveryPage = new ArrayList<>();
        int lastPageInt = Integer.parseInt(lastPage);

        if (lastPageInt > PAGES_TO_CHECK) {
            lastPageInt = PAGES_TO_CHECK;
        }
        for (int i = 1; i <= lastPageInt; i++) {
            AvitoCallable task = new AvitoCallable(String.valueOf(i), cityInEnglish, product);
            Future<List<Double>> future = EXECUTOR_SERVICE.submit(task);  // Здесь запускаем таски
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

        List<Double> listOfPrices = listOfResultsOnEveryPage
                .stream()
                .filter(list -> list != null)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return calculateAveragePriceFromList(listOfPrices);
    }

    private double calculateAveragePriceFromList(List<Double> listOfPrices) {

        double averagePriceCommon = listOfPrices
                .stream()
                .mapToDouble(e -> e)
                .average()
                .getAsDouble();

        double deletionThreshold = (averagePriceCommon / 100) * PRICE_THRESHOLD;
        return listOfPrices
                .stream()
                .filter(p -> (averagePriceCommon - p) < deletionThreshold)
                .mapToDouble(p -> p)
                .average()
                .getAsDouble();
    }

    private InlineKeyboardMarkup getInlineKeyboardMarkup(String URL) {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Перейти на Avito");
        button.setCallbackData("Button has been pressed");
        button.setUrl(URL);

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(button);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        keyboard.setKeyboard(rowList);
        return keyboard;
    }

    private HashMap<String, String> fillListWithCities() {

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
            put("горный", "gorno-altaysk");
            put("горно-алтай", "gorno-altaysk");
            put("горный-алтай", "gorno-altaysk");
            put("горно-алтайск", "gorno-altaysk");
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

    // public static SendMessage sendInlineKeyBoardMessage(String chatId) {
    //     InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
    //     InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
    //     InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
    //     inlineKeyboardButton1.setText("Тык");
    //     inlineKeyboardButton1.setCallbackData("Button \"Тык\" has been pressed");
    //
    //     inlineKeyboardButton2.setText("Тык2");
    //     inlineKeyboardButton2.setCallbackData("Button \"Тык2\" has been pressed");
    //
    //     List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
    //     List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
    //
    //     keyboardButtonsRow1.add(inlineKeyboardButton1);
    //     keyboardButtonsRow2.add(inlineKeyboardButton2);
    //
    //     List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    //     rowList.add(keyboardButtonsRow1);
    //     rowList.add(keyboardButtonsRow2);
    //     inlineKeyboardMarkup.setKeyboard(rowList);
    //     SendMessage sendMessage = new SendMessage();
    //     sendMessage.setChatId(chatId);
    //     sendMessage.setReplyMarkup(inlineKeyboardMarkup);
    //
    //     return sendMessage;
    // }


}
