package ru.beloshitsky.telegrambot.messages;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.beloshitsky.telegrambot.configuration.BotConfig;
import ru.beloshitsky.telegrambot.util.KeyboardMarkup;
import ru.beloshitsky.telegrambot.parsers.InputParser;
import ru.beloshitsky.telegrambot.services.AvgPriceCalculator;

import java.util.Map;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class AveragePriceMessage implements Message {
  AvgPriceCalculator avgPriceCalculator;
  InputParser inputParser;
  BotConfig botConfig;
  KeyboardMarkup keyboard;

  public void generateMessage(SendMessage message, String text) {
    Map<String, String> parsedInput = inputParser.getParsedInput(text);

    if (parsedInput == null) {
      message.setText("Нет такого города");
      return;
    }
    String city = parsedInput.get("city");
    String product = parsedInput.get("product");
    String cityInEnglish = parsedInput.get("cityInEnglish");
    double averagePrice = avgPriceCalculator.getAvgPrice(cityInEnglish, product);

    if (averagePrice == 0) {
      message.setText(String.format("В городе %s нет товара %s", city, product));
      return;
    }
    message.setText(String.format("Средняя цена в городе %s = %,.0f ₽", city, averagePrice));
    message.setReplyMarkup(keyboard.getAvitoLinkMarkup(cityInEnglish, product));
  }

  @Override
  public String getId() {
    return "найти среднюю цену";
  }
}
