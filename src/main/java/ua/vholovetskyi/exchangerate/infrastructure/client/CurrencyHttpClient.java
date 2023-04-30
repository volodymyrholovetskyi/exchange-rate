package ua.vholovetskyi.exchangerate.infrastructure.client;

import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;

import java.util.List;

interface CurrencyHttpClient {

    List<ExchangeRateDto> getExchangeRate();
}
