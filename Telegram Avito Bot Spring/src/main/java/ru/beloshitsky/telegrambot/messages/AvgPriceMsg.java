package ru.beloshitsky.telegrambot.messages;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.beloshitsky.telegrambot.advices.annotations.LogArgs;
import ru.beloshitsky.telegrambot.messages.handlers.AvgPriceMsgHandler;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class AvgPriceMsg implements Message {
  
  AvgPriceMsgHandler avgPriceMsgHandler;
  
  @LogArgs
  public void generateMessage(SendMessage message, String text) {
    String answer = avgPriceMsgHandler.handeMessage(message, text);
    message.setText(answer);
  }

  @Override
  public String getId() {
    return "найти среднюю цену";
  }
}
