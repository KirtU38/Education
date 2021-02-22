import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Bot extends TelegramLongPollingBot {

    private static final String TOKEN = "1643407471:AAGlk8FB3HmCGnkgLq8iU5tjDxvFfFexfsg";
    private static final String USER_NAME = "avito_price_bot";

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

        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message;
            String chatId = String.valueOf(update.getMessage().getChatId());

            // Кнопка
            // KeyboardButton button = new KeyboardButton();
            // button.setText("Test");
            //
            // // Ряд
            // KeyboardRow row = new KeyboardRow();
            // row.add(button);
            // List<KeyboardRow> listOfRows = new ArrayList<>();
            // listOfRows.add(row);
            //
            // ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            // replyKeyboardMarkup.setKeyboard(listOfRows);
            // SendMessage keyboard = new SendMessage();
            // keyboard.setChatId(chatId);
            // keyboard.setReplyMarkup(replyKeyboardMarkup);
            // execute(keyboard);


            String userInput = update.getMessage().getText();
            String[] tokens = userInput.toLowerCase(Locale.ROOT).trim().split("\\s", 2);
            String city = tokens[0];
            String product = tokens[1];
            // String product = Main.mapOfCities.containsKey(city) ? tokens[1] : "";

            if (Main.mapOfCities.containsKey(city)) {
                message = new SendMessage(chatId,
                        "Считаю...");
                execute(message);
                String cityInEnglish = Main.mapOfCities.get(city);
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
            execute(message);
        }
    }

    @Override
    public void onRegister() {
    }

    private static Double findAveragePrice(String product, String cityInEnglish, Elements pages)
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

    public static Double calculateAverageOnAllPages(String lastPage, String cityInEnglish, String product)
            throws InterruptedException {

        List<Future<List<Double>>> listOfTasks = new ArrayList<>();
        List<List<Double>> listOfResultsOnEveryPage = new ArrayList<>();
        int lastPageInt = Integer.parseInt(lastPage);

        if (lastPageInt > Main.PAGES_TO_CHECK) {
            lastPageInt = Main.PAGES_TO_CHECK;
        }
        for (int i = 1; i <= lastPageInt; i++) {
            AvitoCallable task = new AvitoCallable(String.valueOf(i), cityInEnglish, product);
            Future<List<Double>> future = Main.EXECUTOR_SERVICE.submit(task);  // Здесь запускаем таски
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

    public static double calculateAveragePriceFromList(List<Double> listOfPrices) {

        double averagePriceCommon = listOfPrices
                .stream()
                .mapToDouble(e -> e)
                .average()
                .getAsDouble();

        double deletionThreshold = (averagePriceCommon / 100) * Main.PRICE_THRESHOLD;
        return listOfPrices
                .stream()
                .filter(p -> (averagePriceCommon - p) < deletionThreshold)
                .mapToDouble(p -> p)
                .average()
                .getAsDouble();
    }

    public static InlineKeyboardMarkup getInlineKeyboardMarkup(String URL) {
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
