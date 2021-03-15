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
public class AvgPriceMessageService {
  BotConfig botConfig;
  AvitoHTMLParser avitoHTMLParser;

  public double getAvgOnAllPages(String cityInEnglish, String product) {
    List<List<Double>> listOfPricesOnEveryPage = new ArrayList<>();

    for (int page = 1; page <= botConfig.getPagesLimit(); page++) {
      String URLCityPageProduct =
          botConfig.getRootURL() + cityInEnglish + "?p=" + page + "&q=" + product;
      List<Double> listOfPricesOnPage = avitoHTMLParser.getListOfPricesFromURL(URLCityPageProduct);

      if (listOfPricesOnPage == null) {
        break;
      }
      listOfPricesOnEveryPage.add(listOfPricesOnPage);
    }

    if (listOfPricesOnEveryPage.size() == 0) {
      return 0;
    }
    return calculateAvgPriceFromAllPages(listOfPricesOnEveryPage);
  }

  private double calculateAvgPriceFromAllPages(List<List<Double>> listOfResultsOnEveryPage) {
    List<Double> listOfPricesFromAllPages =
        listOfResultsOnEveryPage.stream()
            .filter(list -> list != null)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

    double averagePriceCommon =
        listOfPricesFromAllPages.stream().mapToDouble(e -> e).average().getAsDouble();

    double deletionThreshold = (averagePriceCommon / 100) * botConfig.getPriceThreshold();
    double averagePriceFiltered =
        listOfPricesFromAllPages.stream()
            .filter(p -> (averagePriceCommon - p) < deletionThreshold)
            .mapToDouble(p -> p)
            .average()
            .getAsDouble();

    return averagePriceFiltered;
  }
}
