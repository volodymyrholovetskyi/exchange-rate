package ua.vholovetskyi.exchangerate.application.port;

import ua.vholovetskyi.exchangerate.application.port.dto.ExchangeRateDto;

import java.util.List;

public interface QueryCurrencyUseCase {

    List<ExchangeRateDto> getAverageExchangeRates();
    List<ExchangeRateDto> getAverageExchangeRatesForPeriod(String from, String to);
}
