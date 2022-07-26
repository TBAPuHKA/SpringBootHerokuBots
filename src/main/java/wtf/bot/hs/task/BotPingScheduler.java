package wtf.bot.hs.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import wtf.bot.hs.AppConstants;
import wtf.bot.hs.service.impl.DiscordServiceImpl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
@Service
@EnableScheduling
public class BotPingScheduler {

    @Autowired
    DiscordServiceImpl discordService;

    //FOR TESTs
//    @Scheduled(fixedDelay = 30000L, initialDelay = 30000L)
    //PROD
    @Scheduled(fixedDelay = 1200000L, initialDelay = 1200000L)
    void pingMethod() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.DATE_AND_TIME_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("EET"));
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.getForEntity(AppConstants.HEROKU_URL, String.class);
        } catch (HttpClientErrorException e) {
            log.warn("HttpClientErrorException | StatusCode: " + e.getStatusCode().value());
        } finally {
            discordService.testMessage("Request: DONE | Response: " + (response != null ? response.getStatusCode().value() : "false") + " | " + simpleDateFormat.format(date) + " " + simpleDateFormat.getTimeZone().getDisplayName());
            log.info(String.format("%s | %s", "BotScheduler.class", "Request: DONE"));
        }
    }
}