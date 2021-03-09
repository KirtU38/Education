package ru.beloshitsky.telegrambot.messages;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class StartMessage implements Message {

  @Override
  public SendMessage getMessage(String text, String chatId) {
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
    SendMessage message =
        new SendMessage(
            chatId,
            "Привет! Чтобы узнать среднюю ценю любого товара на Avito, "
                + "просто введи Город, а потом Товар:\n"
                + "Питер Samsung Galaxy S20");
    message.setReplyMarkup(replyKeyboardMarkup);

    return message;
  }

  @Override
  public String getId() {
    return "/start";
  }
}
