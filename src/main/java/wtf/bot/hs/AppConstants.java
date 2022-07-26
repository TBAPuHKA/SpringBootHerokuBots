package wtf.bot.hs;

public class AppConstants {


    public static final String TELEGRAM_TOKEN = System.getenv("TELEGRAM_TOKEN");
    public static final long DISCORD_CHANNEL_LOG = Long.parseLong(System.getenv("DISCORD_CHANNEL_LOG"));
    public static final long DISCORD_CHANNEL_TEST = Long.parseLong(System.getenv("DISCORD_CHANNEL_TEST"));
    public static final String HEROKU_URL = System.getenv("HEROKU_URL");
    public static final String TELEGRAM_USERNAME = System.getenv("TELEGRAM_USERNAME");
    private static final String SERVER_PRODUCT = System.getenv("IS_SERVER_PRODUCT");
    public static final boolean IS_SERVER_PRODUCT = (SERVER_PRODUCT != null && SERVER_PRODUCT.equals("Y"));
    public static final String DISCORD_TOKEN = IS_SERVER_PRODUCT ? System.getenv("DISCORD_TOKEN") : System.getenv("DISCORD_TOKEN_TEST");

    public static final String PB_CURRENCY_FP =  "https://acp.privatbank.ua/api/proxy/currency/";
    public static final String PB_CURRENCY_ARCHIVE = "https://api.privatbank.ua/p24api/exchange_rates?json&date=04.04.2022";
    public static final String PB_CURRENCY_ID5 = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=5";
    public static final String PB_CURRENCY_ID11 = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";
    public static final String TEST_MESSAGE_FORMAT = "[%s] | %s | %s | %d ms";
    public static final String LOGGING_MESSAGE_FORMAT = "[%s] | %s | %s";
    public final static String DATE_FORMAT = "dd-MM-yy";
    public final static String TIME_FORMAT = "HH:mm:ss";
    public final static String DATE_AND_TIME_FORMAT = String.format("%s %s", DATE_FORMAT, TIME_FORMAT);



}
