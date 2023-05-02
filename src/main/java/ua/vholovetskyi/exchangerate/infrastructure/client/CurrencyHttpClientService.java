package ua.vholovetskyi.exchangerate.infrastructure.client;

import lombok.AllArgsConstructor;
import ua.vholovetskyi.exchangerate.application.port.ExchangeRateFetchUseCase;
import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;

import java.util.List;

@AllArgsConstructor
public class CurrencyHttpClientService implements ExchangeRateFetchUseCase {

    private final List<CurrencyHttpClient> httpClients;

    @Override
    public List<ExchangeRateDto> fetchExchangeRates() {
        return httpClients
                .stream()
                .flatMap(httpClient -> httpClient.getAllExchangeRate().stream())
                .toList();
    }
}
