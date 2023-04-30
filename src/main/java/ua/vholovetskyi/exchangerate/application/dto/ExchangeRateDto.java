package ua.vholovetskyi.exchangerate.application.dto;

import ua.vholovetskyi.exchangerate.domain.Bank;
import ua.vholovetskyi.exchangerate.domain.ExchangeRate;
import ua.vholovetskyi.exchangerate.domain.Money;

import java.time.LocalDate;

public record ExchangeRateDto(
        Bank bank,
        Money amount,
        LocalDate createAt
) {

    public ExchangeRate toExchangeRate() {
        return new ExchangeRate(bank, amount, createAt());
    }
}
