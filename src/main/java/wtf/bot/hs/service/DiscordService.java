package wtf.bot.hs.service;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.TextChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import wtf.bot.hs.events.DiscordListener;
import javax.security.auth.login.LoginException;
import java.util.Date;

@Slf4j
@Service
public class DiscordService {

    //@Value("${discord.token}")
    private static final String DISCORD_TOKEN = System.getenv("DISCORD_TOKEN");
    private static DiscordService DISCORD_SERVICE;
    public static final String TEST_MESSAGE_FORMAT = "[%s] | %s | %s | %d ms";
    public static final String LOGGING_MESSAGE_FORMAT = "[%s] | %s | %s";
    public static final long MY_TEST_CHANNEL_ID = 900832589340880956L;
    public static final long HORIZONT_TEST_CHANNEL_ID = 827231561682255952L;
    private JDABuilder builder;
    private JDA jda;
    private DiscordListener discordListener;
    private TextChannel textChannel;

    public void startDiscord (){
        try {
            jda = JDABuilder.createDefault(DISCORD_TOKEN).setActivity(Activity.playing("пипиську занзы")).build().awaitReady();
        } catch (InterruptedException | LoginException e) {
            e.printStackTrace();
        }
        this.testMessage("UTBotHorizontApplication | STARTED");
        log.warn(String.format(LOGGING_MESSAGE_FORMAT,"DiscordService.startDiscord()", "DONE","Bot is ONLINE"));

        jda.addEventListener(new DiscordListener());
    }

    private DiscordService(){
        log.warn(String.format(LOGGING_MESSAGE_FORMAT,"DiscordService","DONE","entity CREATED"));
    }

    public static DiscordService getDiscordService(){
        if(DISCORD_SERVICE!=null){
            return DISCORD_SERVICE;
        } else {
            return DISCORD_SERVICE = new DiscordService();
        }
    }

    public void sendMessage(String message) {
        this.textChannel = getTextChannelById((MY_TEST_CHANNEL_ID));
        if (textChannel!=null) {
            this.textChannel.sendMessage(textChannel.toString()+" "+message).queue();
            log.warn(textChannel.toString());
        }
    }

    public TextChannel getTextChannelById (long id){
        return jda.getTextChannelById(id);
    }

    public void testMessage(String message) {
        long time = System.currentTimeMillis();
        this.textChannel = getTextChannelById((MY_TEST_CHANNEL_ID));
        if (textChannel!=null) {
            this.textChannel.sendMessage(String.format(TEST_MESSAGE_FORMAT, message, textChannel.toString(), new Date(), System.currentTimeMillis()-time)).queue();
            log.warn(textChannel.toString());
        }
    }
}
