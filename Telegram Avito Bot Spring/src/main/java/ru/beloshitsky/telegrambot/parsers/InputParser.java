package ru.beloshitsky.telegrambot.parsers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class InputParser {
  
  Map<String, String> mapOfCities;

  public InputParser(@Qualifier("mapOfCities") Map<String, String> mapOfCities) {
    this.mapOfCities = mapOfCities;
  }

  public Map<String, String> getParsedInput(String text) {
    HashMap<String, String> parsedInput = null;
    String[] tokens = text.toLowerCase(Locale.ROOT).trim().split("\\s");
    StringBuilder city = new StringBuilder();
    StringBuilder product = new StringBuilder();

    for (int i = 0; i < tokens.length; i++) {
      city.append(tokens[i]);
      if (mapOfCities.containsKey(city.toString())) {
        for (int j = i + 1; j < tokens.length; j++) {
          product.append(tokens[j]).append("+");
        }
        product.deleteCharAt(product.length() - 1);
        parsedInput = new HashMap<>();
        parsedInput.put("city", city.toString());
        parsedInput.put("cityInEnglish", mapOfCities.get(city.toString()));
        parsedInput.put("product", product.toString());
        break;
      }
      city.append("-");
    }
    return parsedInput;
  }
}
