package egor.bot;

import egor.Main;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class MessageManager implements Runnable {

    private Update update;
    private BotService botService;
    private Bot bot;

    public MessageManager(Update update) {
        this.update = update;
        botService = Main.CONTEXT.getBean("botService", BotService.class);
        bot = Main.CONTEXT.getBean("bot", Bot.class);
    }

    @SneakyThrows
    @Override
    public void run() {

        SendMessage message;
        String text = update.getMessage().getText().toLowerCase(Locale.ROOT);
        String chatId = String.valueOf(update.getMessage().getChatId());

        if (isMessage(update)) {
            switch (text) {
                default:
                    if (matchesCityAndProduct(text)) {
                        message = calculatedAveragePrice(update, chatId);
                    } else {
                        message = wrongMessage(chatId);
                    }
                    break;
                case "помощь":
                    message = helpMessage(chatId);
                    break;
                case "/start":
                    message = startMessage(chatId);
            }
            bot.execute(message);
        }
    }

    private boolean matchesCityAndProduct(String text) {
        return text.matches("[а-яА-Я]+-*\\s*[а-яА-Я]*-*[а-яА-Я]*\\s+.*");
    }

    private SendMessage wrongMessage(String chatId) {

        return new SendMessage(chatId, "Не понимаю вас, пожалуйста введите город, потом товар");
    }

    private SendMessage startMessage(String chatId) {

        // Кнопка
        KeyboardButton button = new KeyboardButton();
        button.setText("Помощь");

        // Ряд
        KeyboardRow row = new KeyboardRow();
        row.add(button);
        List<KeyboardRow> listOfRows = new ArrayList<>();
        listOfRows.add(row);

        // Клавиатура
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(listOfRows);
        SendMessage message = new SendMessage(chatId,
                "Привет! Чтобы узнать среднюю ценю любого товара на Avito, просто введи Город, а потом Товар:\n" +
                        "Питер Samsung Galaxy S20");
        message.setReplyMarkup(replyKeyboardMarkup);
        return message;
    }

    private boolean isMessage(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }

    private SendMessage helpMessage(String chatId) {

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

        SendMessage message = new SendMessage();
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

        if (botService.getMapOfCities().containsKey(city)) {
            message = new SendMessage(chatId,
                    "Считаю...");
            bot.execute(message);
            String cityInEnglish = botService.getMapOfCities().get(city);
            String URL = "https://www.avito.ru/" + cityInEnglish + "?q=" + product;

            Document htmlDoc;
            synchronized (MessageManager.class) {
                System.out.println("GETTING URL");
                htmlDoc = Jsoup.connect(URL).get();
                Thread.sleep(BotSettings.DELAY_BETWEEN_CONNECTIONS);
            }

            if (htmlDoc != null) {
                Elements pages = htmlDoc.select("span[data-marker~=page[(]\\d+[)]]");
                Double averagePrice = getAveragePriceOnAllPages(product, cityInEnglish, pages);
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

    private Double getAveragePriceOnAllPages(String product, String cityInEnglish, Elements pages)
            throws InterruptedException {

        double averagePriceResult;

        if (pages.size() > 0) {
            System.out.println("Многопоток");
            String lastPage = pages.get(pages.size() - 1).text();
            averagePriceResult = calculateAverageOnAllPages(lastPage, cityInEnglish, product);
        } else {
            System.out.println("Один");
            List<Double> listOfPrices = new PageScanner("1", cityInEnglish, product).call();
            averagePriceResult = calculateAveragePriceFromList(listOfPrices);
        }
        return averagePriceResult;
    }

    private Double calculateAverageOnAllPages(String lastPage, String cityInEnglish, String product) {

        List<Future<List<Double>>> listOfTasks = new ArrayList<>();
        List<List<Double>> listOfResultsOnEveryPage = new ArrayList<>();
        int lastPageInt = Integer.parseInt(lastPage);

        if (lastPageInt > BotSettings.PAGES_TO_CHECK) {
            lastPageInt = BotSettings.PAGES_TO_CHECK;
        }
        for (int i = 1; i <= lastPageInt; i++) {
            PageScanner task = new PageScanner(String.valueOf(i), cityInEnglish, product);
            Future<List<Double>> future = botService.getExecutorService().submit(task);  // Здесь запускаем таски
            listOfTasks.add(future);
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

        double deletionThreshold = (averagePriceCommon / 100) * BotSettings.PRICE_THRESHOLD;
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
}
