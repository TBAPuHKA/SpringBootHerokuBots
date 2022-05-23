package wtf.bot.hs.service;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Service;
import wtf.bot.hs.AppConstants;

import javax.inject.Singleton;
import javax.security.auth.login.LoginException;
import java.util.Date;

@Slf4j
@Service
@Singleton
public class DiscordService extends ListenerAdapter {

    //@Value("${discord.token}")

    private static DiscordService DISCORD_SERVICE;
    public static final long MY_TEST_CHANNEL_ID = 900832589340880956L;
    public static final long HORIZONT_TEST_CHANNEL_ID = 827231561682255952L;
    //    private JDABuilder builder;
    private JDA jda;
    //    private DiscordListener discordListener;
    private TextChannel textChannel;
    private static String prefix = "эй ты!";

    public void onMessageReceived(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        if (channel.getIdLong() == getDiscordService().getTextChannelById(MY_TEST_CHANNEL_ID).getIdLong()
                || channel.getIdLong() == getDiscordService().getTextChannelById(HORIZONT_TEST_CHANNEL_ID).getIdLong()) {

            Message msg = event.getMessage();
            if (msg.getContentRaw().equals(prefix)) {

                long time = System.currentTimeMillis();
                channel.sendMessage("вот же тварь!") /* => RestAction<Message> */
                        .queue(response /* => Message */ -> {
                            response.editMessageFormat("Response: %d ms", System.currentTimeMillis() - time).queue();
                        });
            }
        }
    }

    public void startDiscord() {
        try {
            jda = JDABuilder.createDefault(AppConstants.DISCORD_TOKEN).setActivity(Activity.playing("пипиську занзы")).build().awaitReady();
        } catch (InterruptedException | LoginException e) {
            e.printStackTrace();
        }
        this.testMessage("UTBotHorizontApplication | STARTED | " + new Date());
        log.warn(String.format(AppConstants.LOGGING_MESSAGE_FORMAT, "DiscordService.startDiscord()", "DONE", "Bot is ONLINE"));

        jda.addEventListener(new DiscordService());
    }

    private DiscordService() {
        log.warn(String.format(AppConstants.LOGGING_MESSAGE_FORMAT, "DiscordService", "DONE", "entity CREATED"));
    }

    public static DiscordService getDiscordService() {
        if (DISCORD_SERVICE != null) {
            return DISCORD_SERVICE;
        } else {
            return DISCORD_SERVICE = new DiscordService();
        }
    }

    public void sendMessage(String message) {
        this.textChannel = getTextChannelById((MY_TEST_CHANNEL_ID));
        if (textChannel != null) {
            this.textChannel.sendMessage(textChannel.toString() + " " + message).queue();
            log.warn(textChannel.toString());
        }
    }

    public TextChannel getTextChannelById(long id) {
        return jda.getTextChannelById(id);
    }

    public void testMessage(String message) {
        long time = System.currentTimeMillis();
        this.textChannel = getTextChannelById((MY_TEST_CHANNEL_ID));
        if (textChannel != null) {
            this.textChannel.sendMessage(message).queue();
//            this.textChannel.sendMessage(String.format(AppConstants.TEST_MESSAGE_FORMAT, message, textChannel.toString(), new Date(), System.currentTimeMillis() - time)).queue();
            log.warn(textChannel.toString());
        }
    }
}
