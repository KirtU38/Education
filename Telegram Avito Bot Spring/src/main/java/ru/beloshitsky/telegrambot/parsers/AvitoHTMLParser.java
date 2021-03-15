package ru.beloshitsky.telegrambot.parsers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import ru.beloshitsky.telegrambot.configuration.BotConfig;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class AvitoHTMLParser {
  Logger log;
  BotConfig botConfig;
  AvitoTagsParser tagsParser;

  public List<Double> getListOfPricesFromURL(String URLCityPageProduct) {
    Document htmlDoc = getHTML(URLCityPageProduct);
    if (htmlDoc == null) {
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
    try {
      htmlDoc =
          Jsoup.connect(URL)
              .userAgent(
                  "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36")
              .referrer("http://www.google.com")
              .get();
    } catch (IOException e) {
      log.error("Couldn't fetch the URL");
      e.printStackTrace();
      return null;
    }
    return htmlDoc;
  }
}
