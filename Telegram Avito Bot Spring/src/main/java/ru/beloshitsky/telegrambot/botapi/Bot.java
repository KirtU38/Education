package ru.beloshitsky.telegrambot.botapi;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
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
public class Bot extends TelegramWebhookBot {
  BotService botService;
  BotConfig botConfig;

  @Override
  public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
    return botService.processUpdate(update);
  }

  @Override
  public String getBotUsername() {
    return botConfig.getUserName();
  }

  @Override
  public String getBotToken() {
    return botConfig.getToken();
  }

  @Override
  public String getBotPath() {
    return botConfig.getWebHookPath();
  }

  @PostConstruct
  public void registerBot() {
    try {
      TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
      api.registerBot(this, new SetWebhook(botConfig.getWebHookPath()));
      log.info("Bot Registered");
    } catch (TelegramApiException e) {
      log.error("Couldn't register a Bot");
      e.printStackTrace();
    }
  }
}
