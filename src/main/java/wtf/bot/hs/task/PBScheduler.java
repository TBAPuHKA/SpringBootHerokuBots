package wtf.bot.hs.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import wtf.bot.hs.AppConstants;
import wtf.bot.hs.dto.CurrencyDTO;
import wtf.bot.hs.model.enums.CurrencyType;
import wtf.bot.hs.service.DiscordService;
import wtf.bot.hs.util.ForeignExchangeUtil;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
@EnableScheduling
public class PBScheduler {

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

        DiscordService.getDiscordService().testMessage(stringJoiner.toString());
        log.info(String.format(AppConstants.LOGGING_MESSAGE_FORMAT, "PBScheduler", "getCurrency", "OK"));

    }
}