package ru.beloshitsky.telegrambot.messages;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class StartMessage implements Message {
  KeyboardMarkup keyboardMarkup;

  @Override
  public void generateMessage(SendMessage message, String text) {
    message.setText(
        "Привет! Чтобы узнать среднюю ценю любого товара на Avito, "
            + "просто введи Город, а потом Товар:\n"
            + "Питер Samsung Galaxy S20");
    message.setReplyMarkup(keyboardMarkup.getHelpButtonMarkup());
  }

  @Override
  public String getId() {
    return "/start";
  }
}
