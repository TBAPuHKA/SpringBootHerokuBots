package wtf.bot.hs.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import wtf.bot.hs.service.DiscordService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import static wtf.bot.hs.service.DiscordService.*;

@Slf4j
@Service
@EnableScheduling
public class BotScheduler {

    @Scheduled(fixedDelay = 300000L, initialDelay = 300000L)
//1800000L
    void pingMethod() throws IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL("https://horizont-bot.herokuapp.com/").openConnection();
        connection.setRequestMethod("HEAD");
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            DiscordService.getDiscordService().testMessage(String.format(LOGGING_MESSAGE_FORMAT,"BotScheduler.class", "PING: "+responseCode,"NOT OK"));
        } else {
            DiscordService.getDiscordService().testMessage(String.format(LOGGING_MESSAGE_FORMAT,"BotScheduler.class",  "PING: "+responseCode,"OK"));
        }

        //DiscordService.getDiscordService().testMessage("BotScheduler | PING");
        log.info(String.format(LOGGING_MESSAGE_FORMAT,"BotScheduler.class", "PING","OK"));

    }
}



//        String[] hostList = { "http://crunchify.com", "http://yahoo.com", "http://www.ebay.com",
//                "https://google.com",
//                "http://www.example.co", "https://paypal.com",
//                "http://bing.com/", "http://techcrunch.com/", "http://mashable.com/",
//                "https://thenextweb.com/", "http://wordpress.com/",
//                "http://wordpress.org/", "http://example.com/", "http://sjsu.edu/",
//                "https://ebay.co.uk/", "http://google.co.uk/", "http://wikipedia.org/" };
//
//        for (int i = 0; i < hostList.length; i++) {
//
//            String url = hostList[i];
//            getStatus(url);
//
//        }
//
//        System.out.println("Task completed...");
//    }

//    public static String getStatus(String url) throws IOException {

//        String result = "";
//        int code = 200;
//        try {
//            URL siteURL = new URL(url);
//            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
//            connection.setRequestMethod("GET");
//            connection.setConnectTimeout(3000);
//            connection.connect();
//
//            code = connection.getResponseCode();
//            if (code == 200) {
//                result = "-> Green <-\t" + "Code: " + code;
//                ;
//            } else {
//                result = "-> Yellow <-\t" + "Code: " + code;
//            }
//        } catch (Exception e) {
//            result = "-> Red <-\t" + "Wrong domain - Exception: " + e.getMessage();
//
//        }
//        System.out.println(url + "\t\tStatus:" + result);
//        return result;