package ua.vholovetskyi.exchangerate;

import ua.vholovetskyi.commons.utils.FormatterUtils;
import ua.vholovetskyi.exchangerate.application.average.AverageDto;
import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;
import ua.vholovetskyi.exchangerate.application.dto.RichExchangeRateDto;
import ua.vholovetskyi.exchangerate.domain.Bank;
import ua.vholovetskyi.exchangerate.domain.Money;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class BaseExchangerRateTest {

    protected List<ExchangeRateDto> givenListPrivate24ExchangeRate() {
        return List.of(
                new ExchangeRateDto(
                        Bank.PRIVATE_24,
                        Money.create(BigDecimal.valueOf(38.25), BigDecimal.valueOf(37), "USA", "UAH"),
                        FormatterUtils.getCurrentDate()),
                new ExchangeRateDto(
                        Bank.PRIVATE_24,
                        Money.create(BigDecimal.valueOf(38.27), BigDecimal.valueOf(36.55), "USA", "UAH"),
                        FormatterUtils.getCurrentDate()),
                new ExchangeRateDto(
                        Bank.PRIVATE_24,
                        Money.create(BigDecimal.valueOf(36.67), BigDecimal.valueOf(36.65), "USA", "UAH"),
                        FormatterUtils.getCurrentDate()),
                new ExchangeRateDto(
                        Bank.PRIVATE_24,
                        Money.create(BigDecimal.valueOf(36.85), BigDecimal.valueOf(36.65), "USA", "UAH"),
                        FormatterUtils.getCurrentDate()));

    }

    protected Map<Bank, Map<String, RichExchangeRateDto>> givenMapRichExchangeRate() {
        return Map.of(Bank.MONOBANK, Map.of("USA -> UAH", givenRichExchangeRate()));
    }

    protected List<ExchangeRateDto> givenEmptyList() {
        return Collections.emptyList();
    }

    protected RichExchangeRateDto givenRichExchangeRate() {
        return new RichExchangeRateDto(
                List.of(
                        new ExchangeRateDto(
                                Bank.MONOBANK,
                                Money.create(BigDecimal.valueOf(38.34), BigDecimal.valueOf(38.45), "USA", "UAH"),
                                FormatterUtils.getCurrentDate())),
                new AverageDto(BigDecimal.valueOf(36.67), BigDecimal.valueOf(36.65)));
    }

    protected List<ExchangeRateDto> givenRateThreeBanks() {
        return List.of(
                new ExchangeRateDto(
                        Bank.PRIVATE_24,
                        Money.create(BigDecimal.valueOf(38.25), BigDecimal.valueOf(37), "USA", "UAH"),
                        FormatterUtils.getCurrentDate()),
                new ExchangeRateDto(
                        Bank.MINFIN,
                        Money.create(BigDecimal.valueOf(38.27), BigDecimal.valueOf(36.55), "USA", "UAH"),
                        FormatterUtils.getCurrentDate()),
                new ExchangeRateDto(
                        Bank.MONOBANK,
                        Money.create(BigDecimal.valueOf(36.67), BigDecimal.valueOf(36.65), "USA", "UAH"),
                        FormatterUtils.getCurrentDate()));
    }

}
