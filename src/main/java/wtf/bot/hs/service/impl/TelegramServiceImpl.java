package wtf.bot.hs.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import wtf.bot.hs.AppConstants;
import wtf.bot.hs.service.BotService;
import wtf.bot.hs.service.MessageService;

import javax.inject.Singleton;

@Slf4j
@Service
@Singleton
public class TelegramServiceImpl extends TelegramLongPollingBot implements BotService {

    @Override
    public String getBotUsername() {
        return AppConstants.TELEGRAM_USERNAME;
    }

    @Override
    public String getBotToken() {
        return AppConstants.TELEGRAM_TOKEN;
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

// REFACTORING v0.1 ===========================================================================================================

    @Override
    public void startBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramServiceImpl());
            log.warn(String.format(AppConstants.LOGGING_MESSAGE_FORMAT, "TelegramService.startTelegram()", "DONE", "Bot is ONLINE"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getMessage() {

    }

    @Override
    public void sendMessage() {

    }
}
