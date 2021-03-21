package ru.beloshitsky.telegrambot.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.beloshitsky.telegrambot.configuration.BotConfig;
import ru.beloshitsky.telegrambot.parsers.AvitoHTMLParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AvgPriceCalculator {
  BotConfig botConfig;
  AvitoHTMLParser avitoHTMLParser;

  public double getAvgPrice(String cityInEnglish, String product) {
    List<List<Double>> listOfPricesOnAllPages = getPricesFromEveryPage(cityInEnglish, product);
    if (listOfPricesOnAllPages.size() == 0) {
      return 0;
    }
    return avgPriceFromAllPages(listOfPricesOnAllPages);
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

  public double avgPriceFromAllPages(List<List<Double>> listOfResultsOnEveryPage) {
    List<Double> listOfPricesFromAllPages =
        listOfResultsOnEveryPage.stream()
            .filter(list -> list != null)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

    double averagePriceCommon =
        listOfPricesFromAllPages.stream().mapToDouble(e -> e).average().getAsDouble();
    double deletionThreshold = (averagePriceCommon / 100) * botConfig.getPriceThreshold();

    return listOfPricesFromAllPages.stream()
        .filter(p -> (averagePriceCommon - p) < deletionThreshold)
        .mapToDouble(p -> p)
        .average()
        .getAsDouble();
  }
}
