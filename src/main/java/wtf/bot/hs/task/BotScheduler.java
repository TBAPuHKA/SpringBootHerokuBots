package wtf.bot.hs.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import wtf.bot.hs.service.DiscordService;
import static wtf.bot.hs.service.DiscordService.*;

@Slf4j
@Service
@EnableScheduling
public class BotScheduler {

    @Scheduled(fixedDelay = 900000L, initialDelay = 900000L)
//1800000L
    void pingMethod() {
        DiscordService.getDiscordService().testMessage("BotScheduler | PING");
        log.info(String.format(LOGGING_MESSAGE_FORMAT,"BotScheduler.class", "PING",""));

    }
}