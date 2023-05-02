package ua.vholovetskyi.exchangerate.infrastructure.client.dto;

import ua.vholovetskyi.commons.utils.FormatterUtils;
import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;
import ua.vholovetskyi.exchangerate.domain.Bank;
import ua.vholovetskyi.exchangerate.domain.Money;

import java.math.BigDecimal;

public record CurrencyPrivate24Response(
        String ccy,
        String base_ccy,
        BigDecimal buy,
        BigDecimal sale

) {
    public ExchangeRateDto toExchangeRateDto() {
        return new ExchangeRateDto(
                Bank.PRIVATE_24,
                Money.create(buy, sale, ccy, base_ccy),
                FormatterUtils.getCurrentDate()
        );
    }
}
