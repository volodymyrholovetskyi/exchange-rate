package ua.vholovetskyi.exchangerate.application.average;

import org.springframework.stereotype.Service;
import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;

import java.util.List;

@Service
public class AverageService {

    private final Average strategy = new AverageExchangeRate();

    public AverageDto calculateAverage(List<ExchangeRateDto> exchangeRateDto) {
        return strategy.calculate(exchangeRateDto);
    }
}
