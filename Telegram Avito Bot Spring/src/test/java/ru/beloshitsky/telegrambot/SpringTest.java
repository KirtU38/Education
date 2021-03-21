package ru.beloshitsky.telegrambot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.beloshitsky.telegrambot.messages.HelpMessage;
import ru.beloshitsky.telegrambot.messages.StartMessage;
import ru.beloshitsky.telegrambot.messages.WrongCommandMessage;
import ru.beloshitsky.telegrambot.util.KeyboardMarkup;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringTest {

  @Autowired private KeyboardMarkup keyboardMarkup;
  @Autowired private HelpMessage helpMessage;
  @Autowired private WrongCommandMessage wrongMessage;
  @Autowired private StartMessage startMessage;

  @Test
  public void helpMessageTest() {
    SendMessage request = new SendMessage();
    request.setText("помощь");
    request.setChatId("1");

    SendMessage response = new SendMessage();
    response.setText("Введите город, потом товар, например:\nПитер iphone 12 pro max");
    response.setChatId("1");
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
    System.out.println("REQUEST " + request);
    System.out.println("RESPONSE " + response);
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
