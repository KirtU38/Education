package ru.beloshitsky.telegrambot.parsers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import ru.beloshitsky.telegrambot.configuration.BotConfig;
import ru.beloshitsky.telegrambot.messages.AveragePriceMessage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class AvitoHTMLParser {
  BotConfig botConfig;
  AvitoTagsParser tagsParser;
  Logger logError;

  public List<Double> getListOfPricesFromURL(String URLCityPageProduct) {
    Document htmlDoc = getHTML(URLCityPageProduct);
    if(htmlDoc == null){
      return null;
    }
    Elements elementsPrices = tagsParser.selectPrices(htmlDoc);
    List<Double> listOfPricesOnPage = null;

    if (elementsPrices.size() > 0) {
      listOfPricesOnPage =
          elementsPrices.stream()
              .filter(e -> e.text().matches("\\d+.+"))
              .map(e -> Double.parseDouble(e.text().replaceAll("\\W", "")))
              .collect(Collectors.toList());
    }
    return listOfPricesOnPage;
  }

  private Document getHTML(String URL) {
    Document htmlDoc;
    log.info(URL);

    try {
      htmlDoc = Jsoup.connect(URL).get();
    } catch (IOException e) {
      logError.error("Couldn't fetch the URL");
      e.printStackTrace();
      return null;
    }

    return htmlDoc;
  }
}
