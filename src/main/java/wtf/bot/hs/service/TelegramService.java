package wtf.bot.hs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import static wtf.bot.hs.service.DiscordService.*;

@Slf4j
@Service
public class TelegramService  extends TelegramLongPollingBot {

    @Value("${telegram.token}")
    private static final String TELEGRAM_TOKEN = System.getenv("TELEGRAM_TOKEN");
    @Value("${telegram.username}")
    private static final String TELEGRAM_USER_NAME = System.getenv("TELEGRAM_USER_NAME");

    public static void startTelegram (){
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramService());
            log.warn(String.format(LOGGING_MESSAGE_FORMAT,"TelegramService.startTelegram()", "DONE","Bot is ONLINE"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return TELEGRAM_USER_NAME;
    }

    @Override
    public String getBotToken() {
        return TELEGRAM_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText());

            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
