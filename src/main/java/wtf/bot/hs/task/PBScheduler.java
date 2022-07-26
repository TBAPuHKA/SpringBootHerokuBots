package wtf.bot.hs.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import wtf.bot.hs.AppConstants;
import wtf.bot.hs.dto.CurrencyDTO;
import wtf.bot.hs.service.impl.DiscordServiceImpl;
import wtf.bot.hs.util.ForeignExchangeUtil;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
@EnableScheduling
public class PBScheduler {

    @Autowired
    DiscordServiceImpl discordService;

    //FOR TESTs
//    @Scheduled(fixedDelay = 30000L, initialDelay = 30000L)
    //PROD
    @Scheduled(fixedDelay = 3600000L, initialDelay = 3600000L)
    void getCurrency() {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        ForeignExchangeUtil feu = new ForeignExchangeUtil();
        List<CurrencyDTO> currencyDTOList = feu.getCurrencyList();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.DATE_AND_TIME_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("EET"));

        stringJoiner.add("```arm");
        stringJoiner.add("PrivatBank currency to #UAH:");
        currencyDTOList.forEach(e -> {
            stringJoiner.add(String.format("Currency: %s | Sale: %.2f | Buy: %.2f", e.getCcy(), e.getSale(), e.getBuy()));
        });
        stringJoiner.add(simpleDateFormat.format(date) + " " + simpleDateFormat.getTimeZone().getDisplayName());
        stringJoiner.add(" ```");

//        DiscordServiceImpl.getDiscordService().testMessage(stringJoiner.toString());
        discordService.testMessage(stringJoiner.toString());
        log.info(String.format(AppConstants.LOGGING_MESSAGE_FORMAT, "PBScheduler", "getCurrency", "OK"));

    }
}