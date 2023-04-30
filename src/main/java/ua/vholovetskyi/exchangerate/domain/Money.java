package ua.vholovetskyi.exchangerate.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Embeddable
@Getter
@Setter
public class Money {

    private static final String DEFAULT_CURRENCY = "nil";
    private static final String ARROW = " -> ";
    private static final Money DEFAULT_MONEY = new Money(BigDecimal.ZERO, BigDecimal.ZERO, DEFAULT_CURRENCY, DEFAULT_CURRENCY);
    private BigDecimal rateBuy;
    private BigDecimal rateSell;
    private String currencyCode;
    private String baseCurrencyCode;

    private Money(BigDecimal rateBuy, BigDecimal rateSell, String currency, String baseCurrency) {
        this.rateBuy = rateBuy;
        this.rateSell = rateSell;
        this.currencyCode = currency;
        this.baseCurrencyCode = baseCurrency;
    }

    public static Money create(BigDecimal rateBuy, BigDecimal rateSell, String currency, String baseCurrency) {
        if (rateBuy.compareTo(BigDecimal.ZERO) > 0 && rateSell.compareTo(BigDecimal.ZERO) > 0) {
            return new Money(rateBuy, rateSell, currency.toUpperCase(), baseCurrency.toUpperCase());
        }
        return DEFAULT_MONEY;
    }

    public boolean isDefaultMoney(Money amount) {
        return DEFAULT_MONEY.equals(amount);
    }

    public String fullCurrencyCode() {
        return currencyCode + ARROW + baseCurrencyCode;
    }
}
