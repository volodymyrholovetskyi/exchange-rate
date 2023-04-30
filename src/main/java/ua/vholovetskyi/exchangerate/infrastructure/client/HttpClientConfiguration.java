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
    List<CurrencyHttpClient> getCurrencyHttpClient(RestTemplate restTemplate, QueryPropertyPrivate24 p24, QueryPropertyMinfin minfin, QueryPropertyMonobank monobank) {
        return List.of(
                new Private24HttpClient(restTemplate, p24),
                new MonoHttpClient(restTemplate, monobank),
                new MinfinHttpClient(restTemplate, minfin)
                );
    }

    @Bean
    public CurrencyHttpClientService getCommandExchangeRate(List<CurrencyHttpClient> currencyHttpClients) {
        return new CurrencyHttpClientService(currencyHttpClients);
    }
}
