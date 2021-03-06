package ru.beloshitsky.telegrambot.messages;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Message {

    SendMessage getMessage(String text, String chatId);

    String getId();
}
