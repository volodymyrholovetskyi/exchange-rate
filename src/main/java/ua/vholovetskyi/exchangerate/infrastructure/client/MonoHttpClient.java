package ua.vholovetskyi.exchangerate.infrastructure.client.currency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;
import ua.vholovetskyi.exchangerate.infrastructure.client.currency.dto.CurrencyMonoResponse;
import ua.vholovetskyi.exchangerate.infrastructure.client.query.QueryParamiter;

import java.util.Collections;
import java.util.List;

@Slf4j
public class CurrencyMonoHttpClient extends BaseCurrencyHttpClient {
    public CurrencyMonoHttpClient(RestTemplate restTemplate, QueryParamiter params) {
        super(restTemplate, params);
    }

    @Override
    public List<ExchangeRateDto> getExchangeRate() {
        try {
            final ResponseEntity<List<CurrencyMonoResponse>> exchange = restTemplate.exchange(getBaseUrl(), HttpMethod.GET, getRequestEntity(),
                    new ParameterizedTypeReference<>() {
                    });

            final List<CurrencyMonoResponse> result = exchange.getBody();
            if (result == null) {
                log.info("Response Body was null returning empty list");
                return Collections.emptyList();
            }
            log.info("Success Response Body Returned: " + result);
            return result
                    .stream()
                    .map(CurrencyMonoResponse::toExchangeRateDto)
                    .toList();
        } catch (ResourceAccessException e) {
            log.error("[Monobank]: Error while fetching exchange rate using http client: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
