package ua.vholovetskyi.exchangerate.infrastructure.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import ua.vholovetskyi.exchangerate.infrastructure.client.currency.CurrencyHttpClient;
import ua.vholovetskyi.exchangerate.infrastructure.client.currency.CurrencyMinfinHttpClient;
import ua.vholovetskyi.exchangerate.infrastructure.client.currency.CurrencyMonoHttpClient;
import ua.vholovetskyi.exchangerate.infrastructure.client.currency.CurrencyPrivate24HttpClient;
import ua.vholovetskyi.exchangerate.infrastructure.client.query.QueryParamiterMinfin;
import ua.vholovetskyi.exchangerate.infrastructure.client.query.QueryParamiterMonobank;
import ua.vholovetskyi.exchangerate.infrastructure.client.query.QueryParamiterPrivate24;

import java.time.Duration;
import java.util.List;

@Configuration
@PropertySource("classpath:http_client.properties")
public class RestTemplateConfig {

    @Bean
    public HttpClientResponseErrorHandler restTemplateResponseErrorHandler() {
        return new HttpClientResponseErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate(@Value("${exchange-rate.http.client.config.connectionTimeout}") long connectionTimeout,
                                     @Value("${exchange-rate.http.client.config.readTimeout}") long readTimeout,
                                     HttpClientResponseErrorHandler responseErrorHandler) {
        return new RestTemplateBuilder()
                .errorHandler(responseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(connectionTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    @Bean
    List<CurrencyHttpClient> getCurrencyHttpClient(RestTemplate restTemplate, QueryParamiterPrivate24 p24, QueryParamiterMinfin minfin, QueryParamiterMonobank monobank) {
        return List.of(
                new CurrencyPrivate24HttpClient(restTemplate, p24),
                new CurrencyMonoHttpClient(restTemplate, monobank),
                new CurrencyMinfinHttpClient(restTemplate, minfin)
                );
    }

    @Bean
    public CurrencyHttpClientService getCommandExchangeRate(List<CurrencyHttpClient> currencyHttpClients) {
        return new CurrencyHttpClientService(currencyHttpClients);
    }
}
