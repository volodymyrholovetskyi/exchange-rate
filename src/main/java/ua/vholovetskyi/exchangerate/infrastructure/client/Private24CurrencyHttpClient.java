package ua.vholovetskyi.exchangerate.infrastructure.client;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;
import ua.vholovetskyi.exchangerate.infrastructure.client.dto.CurrencyPrivate24Response;
import ua.vholovetskyi.exchangerate.infrastructure.client.query.QueryProperty;

import java.util.Collections;
import java.util.List;

@Slf4j
@NoArgsConstructor
class Private24CurrencyHttpClient extends BaseCurrencyHttpClient {
    public Private24CurrencyHttpClient(RestTemplate restTemplate, QueryProperty params) {
        super(restTemplate, params);
    }

    @Override
    public List<ExchangeRateDto> getAllExchangeRate() {
        try {
            final var exchange = restTemplate.exchange(getBaseUrl(), HttpMethod.GET, getRequestEntity(),
                    new ParameterizedTypeReference<List<CurrencyPrivate24Response>>() {
                    });

            final var result = exchange.getBody();
            if (result == null) {
                log.info("Response Body was null returning empty list");
                return Collections.emptyList();
            }
            log.info("Success Response Body Returned: " + result);
            return result
                    .stream()
                    .map(CurrencyPrivate24Response::toExchangeRateDto)
                    .toList();
        } catch (ResourceAccessException e) {
            log.error("Error while fetching exchange rate from [Private24] using http client: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
