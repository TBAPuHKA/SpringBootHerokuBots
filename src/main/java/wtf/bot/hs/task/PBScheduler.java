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

@Slf4j
@Service
@EnableScheduling
public class PBScheduler {

    @Scheduled(fixedDelay = 1200000L/4, initialDelay = 1200000L/4)
//1800000L
    void getCurrency() throws IOException {

        URL pbCurrencyArchive = new URL(AppConstants.PB_CURRENCY_ARCHIVE);
        URL pbCurrencyID5 = new URL(AppConstants.PB_CURRENCY_ID5);
        URL pbCurrencyID11 = new URL(AppConstants.PB_CURRENCY_ID11);

        HttpURLConnection connectionPBCurrencyID5 = (HttpURLConnection) pbCurrencyID5.openConnection();
        connectionPBCurrencyID5.setRequestMethod("GET");
        connectionPBCurrencyID5.setConnectTimeout(3000);
        connectionPBCurrencyID5.connect();
        String pb_CurrencyID5 = connectionPBCurrencyID5.getContent().toString();

        HttpURLConnection connectionPBCurrencyID11 = (HttpURLConnection) pbCurrencyID11.openConnection();
        connectionPBCurrencyID11.setRequestMethod("GET");
        connectionPBCurrencyID11.setConnectTimeout(3000);
        connectionPBCurrencyID11.connect();
        String pb_CurrencyID11 = connectionPBCurrencyID5.getContent().toString();

        HttpURLConnection connectionPBCurrencyArchive = (HttpURLConnection) pbCurrencyArchive.openConnection();
        connectionPBCurrencyArchive.setRequestMethod("GET");
        connectionPBCurrencyArchive.setRequestProperty("Content-Type", "application/json;charset=cp1251");
        connectionPBCurrencyArchive.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.80 Safari/537.36");
        connectionPBCurrencyArchive.setRequestProperty("id", AppConstants.PB_ID);
        connectionPBCurrencyArchive.setRequestProperty("token", AppConstants.PB_TOKEN);
        connectionPBCurrencyArchive.setConnectTimeout(3000);
        connectionPBCurrencyArchive.connect();
        String pb_CurrencyArchive = connectionPBCurrencyID5.getContent().toString();

        DiscordService.getDiscordService().testMessage(String.format(AppConstants.LOGGING_MESSAGE_FORMAT, pb_CurrencyID5 + System.lineSeparator() + System.lineSeparator(), pb_CurrencyID11 + System.lineSeparator() + System.lineSeparator(),pb_CurrencyArchive + System.lineSeparator() + System.lineSeparator()));
        log.info(String.format(AppConstants.LOGGING_MESSAGE_FORMAT, "PBScheduler", "getCurrency", "OK"));

    }
}