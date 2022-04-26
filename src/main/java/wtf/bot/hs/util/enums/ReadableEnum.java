package wtf.bot.hs.util.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public interface ReadableEnum {
    @JsonValue
    String getTitle();
}
