package ru.beloshitsky.telegrambot.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.beloshitsky.telegrambot.messages.Message;

import java.util.Locale;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class BotService {
  Map<String, Message> mapOfMessages;

  public BotService(@Qualifier("mapOfMessages") Map<String, Message> mapOfMessages) {
    this.mapOfMessages = mapOfMessages;
  }

  public SendMessage processUpdate(Update update) {
    SendMessage message = null;

    if (update.hasMessage() && update.getMessage().hasText()) {
      String text = update.getMessage().getText().toLowerCase(Locale.ROOT);
      String chatId = String.valueOf(update.getMessage().getChatId());
      String command = text;

      if (matchesCityAndProduct(command)) {
        command = "найти среднюю цену";
      }

      message = mapOfMessages.getOrDefault(command, mapOfMessages.get("ошибка")).getMessage(text, chatId);
    }
    return message;
  }

  private boolean matchesCityAndProduct(String text) {
    return text.matches("[а-яА-Я]+-*\\s*[а-яА-Я]*-*[а-яА-Я]*\\s+.*");
  }
}
