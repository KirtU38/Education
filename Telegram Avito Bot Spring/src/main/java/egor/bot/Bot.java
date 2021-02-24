package egor.bot;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Bot extends TelegramLongPollingBot {

    private final BotService botService;

    @Autowired
    public Bot(BotService botService) {
        this.botService = botService;
    }

    @Override
    public String getBotUsername() {
        return BotSettings.USER_NAME;
    }

    @Override
    public String getBotToken() {
        return BotSettings.TOKEN;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("asdasdasd");
        botService.executeUpdate(update);
    }
}
