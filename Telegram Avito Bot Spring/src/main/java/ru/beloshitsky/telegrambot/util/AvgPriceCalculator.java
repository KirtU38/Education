package ru.beloshitsky.telegrambot.util;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.beloshitsky.telegrambot.configuration.BotConfig;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AvgPriceCalculator {

  BotConfig botConfig;

  public double calculateAvgPrice(List<List<Double>> listOfResultsOnEveryPage) {
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
