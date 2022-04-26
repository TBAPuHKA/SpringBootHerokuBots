package wtf.bot.hs.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import wtf.bot.hs.AppConstants;
import wtf.bot.hs.service.DiscordService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@Slf4j
@Service
@EnableScheduling
public class BotScheduler {

    @Scheduled(fixedDelay = 1200000L, initialDelay = 1200000L)
//1800000L
    void pingMethod() throws IOException {

        URL url = new URL(AppConstants.HEROKU_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(3000);
        connection.connect();
        int responseCode = connection.getResponseCode();

        DiscordService.getDiscordService().testMessage(String.format(AppConstants.LOGGING_MESSAGE_FORMAT + " %s", "BotScheduler.class", "PING to " + url + ": " + responseCode, "OK", new Date()));
        log.info(String.format(AppConstants.LOGGING_MESSAGE_FORMAT, "BotScheduler.class", "PING", "OK"));

    }
}