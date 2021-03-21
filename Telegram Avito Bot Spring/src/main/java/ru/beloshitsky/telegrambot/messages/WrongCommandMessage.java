package ru.beloshitsky.telegrambot.messages;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.beloshitsky.telegrambot.advices.annotations.LogArgs;
import ru.beloshitsky.telegrambot.util.KeyboardMarkup;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class WrongCommandMessage implements Message {
    KeyboardMarkup keyboardMarkup;

    @LogArgs
    @Override
    public void generateMessage(SendMessage message, String text) {
        message.setText("Неверная команда");
        message.setReplyMarkup(keyboardMarkup.getHelpButtonMarkup());
    }

    @Override
    public String getId() {
        return "ошибка";
    }
}
