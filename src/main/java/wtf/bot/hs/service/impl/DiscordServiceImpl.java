package wtf.bot.hs.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import wtf.bot.hs.AppConstants;
import wtf.bot.hs.dto.BotDiscordDTO;
import wtf.bot.hs.service.BotService;
import wtf.bot.hs.service.MessageService;

import javax.inject.Singleton;
import javax.security.auth.login.LoginException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;
import java.util.TimeZone;

@Slf4j
@Service
@Scope("singleton")
//@Singleton
public class DiscordServiceImpl extends ListenerAdapter implements BotService {

    private static String botStatus = AppConstants.IS_SERVER_PRODUCT ? "come 2 duck side" : "duck mode ON";
    private static String botPrefix = ">";
//    public static final long MY_TEST_CHANNEL_ID = 900832589340880956L;
    private TextChannel textChannel;
    private static JDA jda;

    public DiscordServiceImpl() {
        log.warn(String.format(AppConstants.LOGGING_MESSAGE_FORMAT, "DiscordService", "DONE", "entity CREATED"));
    }

    public void sendMessage(String message) {
        this.textChannel = getTextChannelById(AppConstants.DISCORD_CHANNEL_LOG);
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
        this.textChannel = getTextChannelById(AppConstants.DISCORD_CHANNEL_LOG);
        if (textChannel != null) {
            this.textChannel.sendMessage(message).queue();
//            this.textChannel.sendMessage(String.format(AppConstants.TEST_MESSAGE_FORMAT, message, textChannel.toString(), new Date(), System.currentTimeMillis() - time)).queue();
            log.warn(textChannel.toString());
        }
    }

// REFACTORING v0.1 ===========================================================================================================

    @Override
    public void startBot() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.DATE_AND_TIME_FORMAT);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("EET"));
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());

        try {
            jda = JDABuilder.createDefault(AppConstants.DISCORD_TOKEN).setActivity(Activity.playing(botStatus)).build().awaitReady();
        } catch (InterruptedException | LoginException e) {
            e.printStackTrace();
        }
        testMessage("```arm" + System.lineSeparator() + "UTBotHorizontApplication | STARTED | " +
                simpleDateFormat.format(new Date()) + " " + simpleDateFormat.getTimeZone().getDisplayName() +
                System.lineSeparator() + "```");
        log.warn(String.format(AppConstants.LOGGING_MESSAGE_FORMAT, "DiscordService.startDiscord()", "DONE", "Bot is ONLINE"));

        jda.addEventListener(new DiscordServiceImpl());
    }

    @Override
    public void getMessage() {

    }

    @Override
    public void sendMessage() {

    }

    public void sendMessage(MessageReceivedEvent event, String content) {
        event.getChannel().sendMessage(content).queue();
    }

    public void onMessageReceived(MessageReceivedEvent event) {

        if (!event.getAuthor().isBot() && event.getMessage().getContentRaw().equals("sick my duck")) {
            sendMessage(event, "greeting comrade");
        }

//        if (dto.getMessageChannel().getIdLong() == this.getTextChannelById(MY_TEST_CHANNEL_ID).getIdLong()
//                || dto.getMessageChannel().getIdLong() == this.getTextChannelById(HORIZONT_TEST_CHANNEL_ID).getIdLong()) {
//
//            Message msg = event.getMessage();
//            if (msg.getContentRaw().equals(prefix)) {
//
//                long time = System.currentTimeMillis();
//                dto.getMessageChannel().sendMessage("вот же тварь!") /* => RestAction<Message> */
//                        .queue(response /* => Message */ -> {
//                            response.editMessageFormat("Response: %d ms", System.currentTimeMillis() - time).queue();
//                        });
//            }
//        }
    }
}
