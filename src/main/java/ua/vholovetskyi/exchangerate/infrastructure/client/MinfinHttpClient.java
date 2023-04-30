package ua.vholovetskyi.exchangerate.infrastructure.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import ua.vholovetskyi.commons.utils.FormatterUtils;
import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;
import ua.vholovetskyi.exchangerate.domain.Bank;
import ua.vholovetskyi.exchangerate.domain.Money;
import ua.vholovetskyi.exchangerate.infrastructure.client.query.QueryProperty;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
//TODO
class MinfinHttpClient extends BaseHttpClient {
    public MinfinHttpClient(RestTemplate restTemplate, QueryProperty params) {
        super(restTemplate, params);
    }

    @Override
    public List<ExchangeRateDto> getExchangeRate() {
        var result = List.of(
                new ExchangeRateDto(
                        Bank.MINFIN,
                        Money.create(BigDecimal.valueOf(36.68), BigDecimal.valueOf(36.65), "USA", "UAH"),
                        FormatterUtils.getCurrencyDate()),
                new ExchangeRateDto(
                        Bank.MINFIN,
                        Money.create(BigDecimal.valueOf(36.64), BigDecimal.valueOf(36.62), "USA", "UAH"),
                        FormatterUtils.getCurrencyDate()));
        log.info("Success Response Body Returned: " + result);
        return result;
    }
}
