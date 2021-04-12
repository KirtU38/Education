package ru.beloshitsky.telegrambot.handlers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.beloshitsky.telegrambot.configuration.BotConfig;
import ru.beloshitsky.telegrambot.parsers.AvitoHTMLParser;
import ru.beloshitsky.telegrambot.parsers.InputParser;
import ru.beloshitsky.telegrambot.util.AvgPriceCalculator;
import ru.beloshitsky.telegrambot.util.KeyboardMarkup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class AvgPriceMsgHandler {

  AvitoHTMLParser avitoHTMLParser;
  AvgPriceCalculator avgPriceCalculator;
  InputParser inputParser;
  BotConfig botConfig;
  KeyboardMarkup keyboard;

  public String handeMessage(SendMessage message, String text) {
    log.info("unparsed: {}", text);
    Map<String, String> parsedInput = inputParser.getParsedInput(text);
    log.info("parsed: {}", parsedInput);
    
    if (parsedInput == null) {
      return "Нет такого города";
    }
    String city = parsedInput.get("city");
    String product = parsedInput.get("product");
    String cityInEnglish = parsedInput.get("cityInEnglish");
    double averagePrice = getAvgPrice(cityInEnglish, product);
    log.info("averagePrice: {}", averagePrice);

    if (averagePrice == 0) {
      return String.format("В городе %s нет товара %s", city, product);
    }
    message.setReplyMarkup(keyboard.getAvitoLinkMarkup(cityInEnglish, product));
    return String.format("Средняя цена в городе %s = %,.0f ₽", city, averagePrice);
  }

  public double getAvgPrice(String cityInEnglish, String product) {
    List<List<Double>> listOfPricesOnAllPages = getPricesFromEveryPage(cityInEnglish, product);
    if (listOfPricesOnAllPages.size() == 0) {
      return 0;
    }
    return avgPriceCalculator.calculateAvgPrice(listOfPricesOnAllPages);
  }

  private List<List<Double>> getPricesFromEveryPage(String cityInEnglish, String product) {
    List<List<Double>> listOfPricesOnAllPages = new ArrayList<>();

    for (int page = 1; page <= botConfig.getPagesLimit(); page++) {
      String URLCityPageProduct =
          botConfig.getRootURL() + cityInEnglish + "?p=" + page + "&q=" + product;

      List<Double> listOfPricesOnPage = avitoHTMLParser.getListOfPricesFromURL(URLCityPageProduct);
      if (listOfPricesOnPage == null) {
        break;
      }
      listOfPricesOnAllPages.add(listOfPricesOnPage);
    }
    return listOfPricesOnAllPages;
  }
}
