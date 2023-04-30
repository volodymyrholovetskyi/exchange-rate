package ua.vholovetskyi.exchangerate.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.vholovetskyi.exchangerate.application.port.QueryCurrencyUseCase;
import ua.vholovetskyi.exchangerate.application.port.dto.ExchangeRateDto;

import java.util.List;

@RestController
@RequestMapping("/exchange-rate/average")
@RequiredArgsConstructor
public class CurrencyController implements QueryCurrencyUseCase {
    private final QueryCurrencyUseCase queryCurrency;

    @Override
    @GetMapping()
    public List<ExchangeRateDto> getAverageExchangeRates() {
        return queryCurrency.getAverageExchangeRates();
    }

    @Override
    @GetMapping("/period")
    public List<ExchangeRateDto> getAverageExchangeRatesForPeriod(@RequestParam String from, @RequestParam String to) {
        return queryCurrency.getAverageExchangeRatesForPeriod(from, to);
    }
}
