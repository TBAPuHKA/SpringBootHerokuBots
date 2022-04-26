package wtf.bot.hs.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import wtf.bot.hs.AppConstants;
import wtf.bot.hs.dto.CurrencyDTO;
import wtf.bot.hs.exception.AppException;
import wtf.bot.hs.model.enums.CurrencyType;

import java.util.*;

@Component
public class ForeignExchangeUtil {

    private static Logger log = LoggerFactory.getLogger(ForeignExchangeUtil.class);
    private static final String USD = "USD";
    private static final String EUR = "EUR";
    private static final String UAH = "UAH";
    private static final String CAD = "CAD";

    public Map<CurrencyType, CurrencyDTO> getCurrencyMapForCurrencyTypeKeys() {
        List<CurrencyDTO> currencyDTOList = getCurrencyList();
        Map<CurrencyType, CurrencyDTO> currencyDTOMap = new HashMap<>();
        currencyDTOList.forEach(e -> {
            currencyDTOMap.put(e.getCcy(), e);
        });
        return currencyDTOMap;
    }

    public List<CurrencyDTO> getCurrencyList() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CurrencyDTO[]> response = restTemplate.getForEntity(AppConstants.PB_CURRENCY_ID11, CurrencyDTO[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    public CurrencyDTO getCurrensyUSD() {
        return getCurrency(CurrencyType.USD);
    }

    public CurrencyDTO getCurrensyEUR() {
        return getCurrency(CurrencyType.EUR);
    }

    public CurrencyDTO getCurrency(CurrencyType currencyType) {
        if(currencyType!=null) {
            return getCurrencyMapForCurrencyTypeKeys().get(currencyType);
        } else {
            throw new AppException("getCurrency() | currencyType cant be NULL");
        }
    }
}
