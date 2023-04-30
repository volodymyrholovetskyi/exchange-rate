package ua.vholovetskyi.exchangerate.infrastructure.client.query;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:http_client.properties")
public class QueryConfig {

    @Bean
    @ConfigurationProperties("exchange-rate.connect.p24")
    public QueryPropertyPrivate24 queryP24() {
        return new QueryPropertyPrivate24();
    }

    @Bean
    @ConfigurationProperties("exchange-rate.connect.monobank")
    public QueryPropertyMonobank queryMonobank() {
        return new QueryPropertyMonobank();
    }

    @Bean
    @ConfigurationProperties("exchange-rate.connect.minfin")
    public QueryPropertyMinfin queryMinfin() {
        return new QueryPropertyMinfin();
    }
}
