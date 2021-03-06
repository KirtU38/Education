package ru.beloshitsky.telegrambot.botapi;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.beloshitsky.telegrambot.configuration.BotConfig;
import ru.beloshitsky.telegrambot.services.BotService;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class Bot extends TelegramLongPollingBot {
  BotService botService;
  BotConfig botConfig;
  Logger logError;

  @Override
  public String getBotUsername() {
    return botConfig.getUserName();
  }

  @Override
  public String getBotToken() {
    return botConfig.getToken();
  }

  @SneakyThrows
  @Override
  public void onUpdateReceived(Update update) {
    SendMessage message = botService.processUpdate(update);
    execute(message);
  }

  @PostConstruct
  public void registerBot() {
    try {
      TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
      botsApi.registerBot(this);
    } catch (TelegramApiException e) {
      logError.error("Couldn't register a Bot");
      e.printStackTrace();
    }
  }
}
