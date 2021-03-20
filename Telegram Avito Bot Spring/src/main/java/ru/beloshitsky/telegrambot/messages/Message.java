package ru.beloshitsky.telegrambot.messages;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Message {
  
  void generateMessage(SendMessage message, String text);

  String getId();
}
