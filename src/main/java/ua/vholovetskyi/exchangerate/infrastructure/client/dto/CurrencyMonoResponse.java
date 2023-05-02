package ua.vholovetskyi.exchangerate.infrastructure.client.dto;

import ua.vholovetskyi.commons.utils.FormatterUtils;
import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;
import ua.vholovetskyi.exchangerate.domain.Bank;
import ua.vholovetskyi.exchangerate.domain.Money;

import java.math.BigDecimal;

public record CurrencyMonoResponse(
        String currencyCodeA,
        String currencyCodeB,

        BigDecimal rateBuy,
        BigDecimal rateSell

) {
    public ExchangeRateDto toExchangeRateDto() {
        return new ExchangeRateDto(
                Bank.MONOBANK,
                Money.create(rateBuy, rateSell, currencyCodeA, currencyCodeB),
                FormatterUtils.getCurrentDate()
        );
    }
}
