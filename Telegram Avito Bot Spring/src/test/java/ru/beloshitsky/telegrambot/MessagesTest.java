package ru.beloshitsky.telegrambot;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.beloshitsky.telegrambot.configuration.BotConfig;
import ru.beloshitsky.telegrambot.messages.HelpMessage;
import ru.beloshitsky.telegrambot.messages.StartMessage;
import ru.beloshitsky.telegrambot.messages.WrongCommandMessage;
import ru.beloshitsky.telegrambot.util.KeyboardMarkup;

import static org.assertj.core.api.Assertions.assertThat;

public class MessagesTest {
  private BotConfig botConfig;
  private KeyboardMarkup keyboardMarkup;
  private HelpMessage helpMessage;
  private WrongCommandMessage wrongMessage;
  private StartMessage startMessage;

  @Before
  public void init() {
    botConfig = Mockito.mock(BotConfig.class);
    keyboardMarkup = new KeyboardMarkup(botConfig);
    helpMessage = new HelpMessage(keyboardMarkup);
    wrongMessage = new WrongCommandMessage(keyboardMarkup);
    startMessage = new StartMessage(keyboardMarkup);
  }

  @Test
  public void helpMessageTest() {
    SendMessage request = new SendMessage();
    request.setText("помощь");
    request.setChatId("1");

    SendMessage response = new SendMessage();
    response.setText("Введите город, потом товар, например:\nПитер iphone 12 pro max");
    response.setChatId("1");
    response.setReplyMarkup(keyboardMarkup.getHelpButtonMarkup());
    helpMessage.generateMessage(request, "помощь");
    assertThat(request).isEqualTo(response);
  }

  @Test
  public void wrongMessageTest() {
    SendMessage request = new SendMessage();
    request.setText("Кто президент Камеруна?");
    request.setChatId("1");

    SendMessage response = new SendMessage();
    response.setText("Неверная команда");
    response.setChatId("1");
    response.setReplyMarkup(keyboardMarkup.getHelpButtonMarkup());
    wrongMessage.generateMessage(request, "Кто президент Камеруна?");
    assertThat(request).isEqualTo(response);
  }

  @Test
  public void startMessageTest() {
    SendMessage request = new SendMessage();
    request.setText("/start");
    request.setChatId("1");

    SendMessage response = new SendMessage();
    response.setText(
        "Привет! Чтобы узнать среднюю ценю любого товара на Avito, "
            + "просто введи Город, а потом Товар:\n"
            + "Питер Samsung Galaxy S20");
    response.setChatId("1");
    response.setReplyMarkup(keyboardMarkup.getHelpButtonMarkup());
    startMessage.generateMessage(request, "Кто президент Камеруна?");
    assertThat(request).isEqualTo(response);
  }
}
