package wtf.bot.hs.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import wtf.bot.hs.service.DiscordService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.ProtocolException;
import java.net.URL;

import static wtf.bot.hs.service.DiscordService.*;

@Slf4j
@Service
@EnableScheduling
public class BotScheduler {

    @Scheduled(fixedDelay = 1200000L, initialDelay = 1200000L)
//1800000L
    void pingMethod() throws IOException {

        URL url = new URL("https://horizont-bot.herokuapp.com/") ;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(3000);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            DiscordService.getDiscordService().testMessage(String.format(LOGGING_MESSAGE_FORMAT,"BotScheduler.class","PING to "+url+": "+responseCode,"NOT OK"));
        } else {
            DiscordService.getDiscordService().testMessage(String.format(LOGGING_MESSAGE_FORMAT,"BotScheduler.class","PING to "+url+": "+responseCode,"OK"));
        }

        //DiscordService.getDiscordService().testMessage("BotScheduler | PING");
        log.info(String.format(LOGGING_MESSAGE_FORMAT,"BotScheduler.class", "PING","OK"));
    }
}