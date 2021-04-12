package ru.beloshitsky.telegrambot.messages;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.beloshitsky.telegrambot.handlers.AvgPriceMsgHandler;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class AvgPriceMsg implements Message {
  
  AvgPriceMsgHandler avgPriceMsgHandler;
  
  public void generateMessage(SendMessage message, String text) {
    log.info("text: {}", text);
    String answer = avgPriceMsgHandler.handeMessage(message, text);
    message.setText(answer);
  }

  @Override
  public String getId() {
    return "найти среднюю цену";
  }
}
