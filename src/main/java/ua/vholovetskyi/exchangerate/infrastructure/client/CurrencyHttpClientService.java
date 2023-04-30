package ua.vholovetskyi.exchangerate.infrastructure.client;

import lombok.AllArgsConstructor;
import ua.vholovetskyi.exchangerate.application.port.ExchangeRateUseCase;
import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;
import ua.vholovetskyi.exchangerate.infrastructure.client.currency.CurrencyHttpClient;

import java.util.List;

@AllArgsConstructor
public class CurrencyHttpClientService implements ExchangeRateUseCase {

    private final List<CurrencyHttpClient> httpClients;

    @Override
    public List<ExchangeRateDto> getExchangeRate() {
        return httpClients
                .stream()
                .flatMap(httpClient -> httpClient.getExchangeRate().stream())
                .toList();
    }
}
