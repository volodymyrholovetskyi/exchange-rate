package ua.vholovetskyi.exchangerate.infrastructure.client.currency;

import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;

import java.util.List;

public interface CurrencyHttpClient {

    List<ExchangeRateDto> getExchangeRate();
}
