package ua.vholovetskyi.exchangerate.application.port.dto;

import ua.vholovetskyi.exchangerate.domain.Bank;
import ua.vholovetskyi.exchangerate.domain.ExchangeRate;
import ua.vholovetskyi.exchangerate.domain.Money;

import java.time.LocalDateTime;

public record ExchangeRateDto(
        Bank bank,
        Money amount,
        LocalDateTime createAt
) {

    public ExchangeRate toExchangeRate() {
        return new ExchangeRate(bank, amount, createAt());
    }
}
