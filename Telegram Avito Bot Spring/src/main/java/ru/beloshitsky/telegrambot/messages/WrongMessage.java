package ru.beloshitsky.telegrambot.messages;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Slf4j
@Component
public class WrongMessage implements Message {

    @Override
    public SendMessage getMessage(String text, String chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Неверная команда");
        return message;
    }

    @Override
    public String getId() {
        return "ошибка";
    }
}
