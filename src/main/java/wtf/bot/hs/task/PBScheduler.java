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
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Slf4j
@Service
@EnableScheduling
public class PBScheduler {

    @Scheduled(fixedDelay = 3600000L, initialDelay = 60L)
//1800000L
    void getCurrency() throws IOException {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        ForeignExchangeUtil feu = new ForeignExchangeUtil();
        List<CurrencyDTO> currencyDTOList = feu.getCurrencyList();

        stringJoiner.add("```arm PrivatBank currency to UAH:");
        currencyDTOList.forEach(e -> {
            stringJoiner.add(String.format("Currency: %s | Sale: %f | Buy: %f", e.getCcy(), e.getSale(), e.getBuy()));
        });
        stringJoiner.add(" ```");

        DiscordService.getDiscordService().testMessage(stringJoiner.toString());
        log.info(String.format(AppConstants.LOGGING_MESSAGE_FORMAT, "PBScheduler", "getCurrency", "OK"));

    }
}