package ru.beloshitsky.telegrambot.messages;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class WrongCommandMessage implements Message {

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
