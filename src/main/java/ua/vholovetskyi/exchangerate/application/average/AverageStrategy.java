package ua.vholovetskyi.exchangerate.application.average;

import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;

import java.util.List;

public interface AverageStrategy {

    AverageDto calculate(List<ExchangeRateDto> exchangeRate);
}
