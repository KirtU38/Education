package ru.beloshitsky.telegrambot.messages.handlers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.beloshitsky.telegrambot.advices.annotations.LogArgsAndRetval;
import ru.beloshitsky.telegrambot.configuration.BotConfig;
import ru.beloshitsky.telegrambot.parsers.InputParser;
import ru.beloshitsky.telegrambot.services.AvgPriceCalculator;
import ru.beloshitsky.telegrambot.util.KeyboardMarkup;

import java.util.Map;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class AvgPriceMsgHandler {
  
  AvgPriceCalculator avgPriceCalculator;
  InputParser inputParser;
  BotConfig botConfig;
  KeyboardMarkup keyboard;

  @LogArgsAndRetval
  public String handeMessage(SendMessage message, String text) {
    Map<String, String> parsedInput = inputParser.getParsedInput(text);
    
    if (parsedInput == null) {
      return "Нет такого города";
    }
    String city = parsedInput.get("city");
    String product = parsedInput.get("product");
    String cityInEnglish = parsedInput.get("cityInEnglish");
    double averagePrice = avgPriceCalculator.getAvgPrice(cityInEnglish, product);

    if (averagePrice == 0) {
      return String.format("В городе %s нет товара %s", city, product);
    }
    message.setReplyMarkup(keyboard.getAvitoLinkMarkup(cityInEnglish, product));
    return String.format("Средняя цена в городе %s = %,.0f ₽", city, averagePrice);
  }
}
