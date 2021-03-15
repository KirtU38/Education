package ru.beloshitsky.telegrambot.messages;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class HelpMessage implements Message {

    @Override
    public SendMessage getMessage(String text, String chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Введите город, потом товар, например:\nПитер iphone 12 pro max");
        return message;
    }

    @Override
    public String getId() {
        return "помощь";
    }
}
