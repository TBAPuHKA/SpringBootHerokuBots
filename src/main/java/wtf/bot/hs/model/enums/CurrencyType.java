package wtf.bot.hs.model.enums;

import wtf.bot.hs.util.enums.PersistableEnum;
import wtf.bot.hs.util.enums.ReadableEnum;

import java.util.Arrays;

public enum CurrencyType implements PersistableEnum, ReadableEnum {
    UAH("UAH", "Uah"),
    USD("USD", "Usd"),
    EUR("EUR", "Eur"),
    CAD("CAD", "Cad"),
    RUR("RUR", "Rur"),
    BTC("BTC", "Btc");

    private final String currencyName;
    private final String title;

    CurrencyType(String currencyName, String title) {
        this.currencyName = currencyName;
        this.title = title;
    }
    public String getCurrencyName() { return currencyName; }

    public static CurrencyType getFromString(String value) {
        return Arrays.stream(CurrencyType.values())
                .filter(t -> t.getCurrencyName().equals(value))
                .findFirst().orElse(null);
    }

    @Override
    public String getValue() {
        return currencyName;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
