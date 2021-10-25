package wtf.bot.hs;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import wtf.bot.hs.service.DiscordService;
import wtf.bot.hs.service.TelegramService;

import java.util.Date;

@Slf4j
@SpringBootApplication
public class UTBotHorizontApplication {

	public static void main(String[] args) {

//===== START [SPRING]
		SpringApplication.run(UTBotHorizontApplication.class, args);

//===== START [TELEGRAM]
		TelegramService.startTelegram();

//===== START [DISCORD]
		DiscordService discordService = DiscordService.getDiscordService();
		discordService.startDiscord();

//===== TESTS

		//discordService.sendMessage(System.getenv("TELEGRAM_USER_NAME") +" "+ System.getenv("JAVA_HOME"));

	}
}
