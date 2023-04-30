package ua.vholovetskyi.exchangerate.application.dto;

import ua.vholovetskyi.exchangerate.application.average.AverageDto;

import java.util.List;

public record RichExchangeRateDto(
        List<ExchangeRateDto> exchangeRates,
        AverageDto average
) {
}
