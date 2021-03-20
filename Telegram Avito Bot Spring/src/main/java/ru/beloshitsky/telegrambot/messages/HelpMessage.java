package ru.beloshitsky.telegrambot.messages;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class HelpMessage implements Message {
  KeyboardMarkup keyboardMarkup;

  @Override
  public void generateMessage(SendMessage message, String text) {
    message.setText("Введите город, потом товар, например:\nПитер iphone 12 pro max");
    message.setReplyMarkup(keyboardMarkup.getHelpButtonMarkup());
  }

  @Override
  public String getId() {
    return "помощь";
  }
}
