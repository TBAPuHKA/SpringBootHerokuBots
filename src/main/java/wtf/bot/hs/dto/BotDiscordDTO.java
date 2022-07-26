package wtf.bot.hs.dto;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;

public class BotDiscordDTO {

    private User user;
    private Member member;
    private Message message;
    private ChannelType channelType;
//    private PrivateChannel privateChannel;
    private MessageChannel messageChannel;
    private TextChannel textChannel;
    private Guild guild;
    private boolean isFromGuild;
    private JDA jda;
    private boolean isWebhookMessage;
    private long messageId;
    private String messageIdString;
    private long responseNumber;

    public BotDiscordDTO(User user, Member member, Message message, ChannelType channelType, MessageChannel messageChannel, TextChannel textChannel, Guild guild, boolean isFromGuild, JDA jda, boolean isWebhookMessage, long messageId, String messageIdString, long responseNumber) {
        this.user = user;
        this.member = member;
        this.message = message;
        this.channelType = channelType;
//        this.privateChannel = privateChannel;
        this.messageChannel = messageChannel;
        this.textChannel = textChannel;
        this.guild = guild;
        this.isFromGuild = isFromGuild;
        this.jda = jda;
        this.isWebhookMessage = isWebhookMessage;
        this.messageId = messageId;
        this.messageIdString = messageIdString;
        this.responseNumber = responseNumber;
    }

    public BotDiscordDTO() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public ChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType;
    }

//    public PrivateChannel getPrivateChannel() {
//        return privateChannel;
//    }
//
//    public void setPrivateChannel(PrivateChannel privateChannel) {
//        this.privateChannel = privateChannel;
//    }

    public MessageChannel getMessageChannel() {
        return messageChannel;
    }

    public void setMessageChannel(MessageChannel messageChannel) {
        this.messageChannel = messageChannel;
    }

    public TextChannel getTextChannel() {
        return textChannel;
    }

    public void setTextChannel(TextChannel textChannel) {
        this.textChannel = textChannel;
    }

    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public boolean isFromGuild() {
        return isFromGuild;
    }

    public void setFromGuild(boolean fromGuild) {
        isFromGuild = fromGuild;
    }

    public JDA getJda() {
        return jda;
    }

    public void setJda(JDA jda) {
        this.jda = jda;
    }

    public boolean isWebhookMessage() {
        return isWebhookMessage;
    }

    public void setWebhookMessage(boolean webhookMessage) {
        isWebhookMessage = webhookMessage;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getMessageIdString() {
        return messageIdString;
    }

    public void setMessageIdString(String messageIdString) {
        this.messageIdString = messageIdString;
    }

    public long getResponseNumber() {
        return responseNumber;
    }

    public void setResponseNumber(long responseNumber) {
        this.responseNumber = responseNumber;
    }
}
