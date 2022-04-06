package wtf.bot.hs;

public class AppConstants {

    public static final String PB_TOKEN = System.getenv("PB_TOKEN");
    public static final String PB_ID = System.getenv("PB_ID");
    public static final String PB_CURRENCY_FP =  "https://acp.privatbank.ua/api/proxy/currency/";
    public static final String PB_CURRENCY_ARCHIVE = "https://api.privatbank.ua/p24api/exchange_rates?json&date=04.04.2022";
    public static final String PB_CURRENCY_ID5 = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=5";
    public static final String PB_CURRENCY_ID11 = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";
    public static final String DISCORD_TOKEN = System.getenv("DISCORD_TOKEN");
    public static final String TELEGRAM_TOKEN = System.getenv("TELEGRAM_TOKEN");
    public static final String TELEGRAM_USER_NAME = System.getenv("TELEGRAM_USER_NAME");
    public static final String TEST_MESSAGE_FORMAT = "[%s] | %s | %s | %d ms";
    public static final String LOGGING_MESSAGE_FORMAT = "[%s] | %s | %s";



}
