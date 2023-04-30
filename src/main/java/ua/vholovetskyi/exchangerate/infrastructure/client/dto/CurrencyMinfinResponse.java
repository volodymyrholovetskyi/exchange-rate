package ua.vholovetskyi.exchangerate.infrastructure.client.dto;

import ua.vholovetskyi.commons.utils.FormatterUtils;
import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;
import ua.vholovetskyi.exchangerate.domain.Bank;
import ua.vholovetskyi.exchangerate.domain.Money;

import java.math.BigDecimal;

public record CurrencyMinfinResponse(
        String currency,
        BigDecimal ask,
        BigDecimal bid
) {

    public ExchangeRateDto toExchangeRateDto() {
        return new ExchangeRateDto(
                Bank.MINFIN,
                Money.create(ask, bid, currency, "UAH"),
                FormatterUtils.getCurrencyDate()
        );
    }
}
