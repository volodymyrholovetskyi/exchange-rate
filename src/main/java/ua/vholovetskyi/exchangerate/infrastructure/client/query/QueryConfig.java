package ua.vholovetskyi.exchangerate.application.query;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:client.properties")
public class QueryConfig {

    @Bean
    @ConfigurationProperties("exchange-rate.connect.p24")
    public QueryParamiterPrivate24 queryP24() {
        return new QueryParamiterPrivate24();
    }

    @Bean
    @ConfigurationProperties("exchange-rate.connect.monobank")
    public QueryParamiterMonobank queryMonobank() {
        return new QueryParamiterMonobank();
    }

    @Bean
    @ConfigurationProperties("exchange-rate.connect.minfin")
    public QueryParamiterMinfin queryMinfin() {
        return new QueryParamiterMinfin();
    }
}
