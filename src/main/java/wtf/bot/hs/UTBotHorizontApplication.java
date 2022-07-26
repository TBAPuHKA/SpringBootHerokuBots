package wtf.bot.hs;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import wtf.bot.hs.service.impl.DiscordServiceImpl;
import wtf.bot.hs.service.impl.TelegramServiceImpl;

@Slf4j
@SpringBootApplication
public class UTBotHorizontApplication {

	public static void main(String[] args) {

//===== START [SPRING]
		SpringApplication.run(UTBotHorizontApplication.class, args);

//===== START [TELEGRAM]
		new TelegramServiceImpl().startBot();

//===== START [DISCORD]
		new DiscordServiceImpl().startBot();
	}
}
