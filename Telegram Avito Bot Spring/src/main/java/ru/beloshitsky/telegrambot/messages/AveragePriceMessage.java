package ru.beloshitsky.telegrambot.messages;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.beloshitsky.telegrambot.configuration.BotConfig;
import ru.beloshitsky.telegrambot.parsers.InputParses;
import ru.beloshitsky.telegrambot.services.AvgPriceCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class AveragePriceMessage implements Message {
  AvgPriceCalculator avgPriceCalculator;
  InputParses inputParses;
  BotConfig botConfig;
  KeyboardMarkup keyboard;

  public void generateMessage(SendMessage message, String text) {
    Map<String, String> parsedInput = inputParses.getParsedInput(text);

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

  private InlineKeyboardMarkup getInlineKeyboardMarkup(String cityInEnglish, String product) {
    // Кнопка
    InlineKeyboardButton button = new InlineKeyboardButton();
    button.setText("Перейти на Avito");
    button.setCallbackData("Button has been pressed");
    String URL = botConfig.getRootURL() + cityInEnglish + "?q=" + product;
    button.setUrl(URL);
    // Ряд
    List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
    keyboardButtonsRow1.add(button);
    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    rowList.add(keyboardButtonsRow1);
    // Клавиатура
    InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
    keyboard.setKeyboard(rowList);
    return keyboard;
  }

  @Override
  public String getId() {
    return "найти среднюю цену";
  }
}
