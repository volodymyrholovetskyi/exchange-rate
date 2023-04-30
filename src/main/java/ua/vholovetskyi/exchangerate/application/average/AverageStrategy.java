package ua.vholovetskyi.exchangerate.application.average;

import ua.vholovetskyi.exchangerate.application.port.dto.ExchangeRateDto;

public interface ExchangeRateAverage {

    AverageDto getAverage(ExchangeRateDto exchangeRate);
}
