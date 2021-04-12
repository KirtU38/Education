package ru.beloshitsky.telegrambot.util;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.beloshitsky.telegrambot.configuration.BotConfig;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class KeyboardMarkup {

  BotConfig botConfig;

  public ReplyKeyboardMarkup getHelpButtonMarkup() {
    // Кнопка
    KeyboardButton button = new KeyboardButton();
    button.setText("Помощь");
    // Ряд
    KeyboardRow row = new KeyboardRow();
    row.add(button);
    List<KeyboardRow> listOfRows = new ArrayList<>();
    listOfRows.add(row);
    // Клавиатура
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    replyKeyboardMarkup.setKeyboard(listOfRows);
    return replyKeyboardMarkup;
  }

  public InlineKeyboardMarkup getAvitoLinkMarkup(String cityInEnglish, String product) {
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
}
