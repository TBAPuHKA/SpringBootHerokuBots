package wtf.bot.hs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import wtf.bot.hs.model.enums.CurrencyType;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDTO {

    private CurrencyType ccy;
    private CurrencyType base_ccy;
    private Double buy;
    private Double sale;

    public CurrencyDTO() {
    }

    public CurrencyDTO(String ccy, String base_ccy, Double buy, Double sale) {
        this.ccy = CurrencyType.valueOf(ccy);
        this.base_ccy = CurrencyType.valueOf(base_ccy);
        this.buy = buy;
        this.sale = sale;
    }

    public CurrencyDTO(CurrencyType ccy, CurrencyType base_ccy, Double buy, Double sale) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;
        this.buy = buy;
        this.sale = sale;
    }

    public CurrencyType getCcy() {
        return ccy;
    }

    public void setCcy(CurrencyType ccy) {
        this.ccy = ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = CurrencyType.valueOf(ccy);
    }

    public CurrencyType getBase_ccy() {
        return base_ccy;
    }

    public void setBase_ccy(CurrencyType base_ccy) {
        this.base_ccy = base_ccy;
    }

    public void setBase_ccy(String base_ccy) {
        this.base_ccy = CurrencyType.valueOf(base_ccy);
    }

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "CurrencyDTO{" +
                "ccy=" + ccy +
                ", base_ccy=" + base_ccy +
                ", buy=" + buy +
                ", sale=" + sale +
                '}';
    }
}
