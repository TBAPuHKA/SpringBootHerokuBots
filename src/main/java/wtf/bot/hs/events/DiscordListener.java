package wtf.bot.hs.events;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import wtf.bot.hs.service.DiscordService;
import static wtf.bot.hs.service.DiscordService.*;

public class DiscordListener extends ListenerAdapter {

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

}
