package ua.vholovetskyi.exchangerate.infrastructure.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;
import ua.vholovetskyi.exchangerate.infrastructure.client.query.QueryPropertyMinfin;
import ua.vholovetskyi.exchangerate.infrastructure.client.query.QueryPropertyMonobank;
import ua.vholovetskyi.exchangerate.infrastructure.client.query.QueryPropertyPrivate24;

import java.time.Duration;
import java.util.List;

@Configuration
@PropertySource("classpath:http_client.properties")
class HttpClientConfiguration {

    @Bean
    public HttpClientErrorHandler restTemplateResponseErrorHandler() {
        return new HttpClientErrorHandler();
    }

    @Bean
    public RestTemplate restTemplate(@Value("${exchange-rate.http.client.config.connectionTimeout}") long connectionTimeout,
                                     @Value("${exchange-rate.http.client.config.readTimeout}") long readTimeout,
                                     HttpClientErrorHandler responseErrorHandler) {
        return new RestTemplateBuilder()
                .errorHandler(responseErrorHandler)
                .setConnectTimeout(Duration.ofMillis(connectionTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    @Bean
    public Private24CurrencyHttpClient getPrivate24(RestTemplate restTemplate, QueryPropertyPrivate24 private24) {
        return new Private24CurrencyHttpClient(restTemplate, private24);
    }

    @Bean
    public MonoCurrencyHttpClient getMonobank(RestTemplate restTemplate, QueryPropertyMonobank monobank) {
        return new MonoCurrencyHttpClient(restTemplate, monobank);
    }

    @Bean
    public MinfinCurrencyHttpClient getMinfin(RestTemplate restTemplate, QueryPropertyMinfin minfin) {
        return new MinfinCurrencyHttpClient(restTemplate, minfin);
    }

    @Bean
    List<CurrencyHttpClient> getCurrencyHttpClient(RestTemplate restTemplate, Private24CurrencyHttpClient p24, MonoCurrencyHttpClient monobank, MinfinCurrencyHttpClient minfin) {
        return List.of(p24, monobank, minfin);
    }

    @Bean
    public CurrencyHttpClientService getCommandExchangeRate(List<CurrencyHttpClient> currencyHttpClients) {
        return new CurrencyHttpClientService(currencyHttpClients);
    }
}
