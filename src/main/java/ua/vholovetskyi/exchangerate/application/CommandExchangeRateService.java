package ua.vholovetskyi.exchangerate.application.strategy;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.vholovetskyi.exchangerate.application.port.ExchangeRateUseCase;

import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class ExchangeRateService {
    private final List<ExchangeRateStrategy> strategy = List.of(
            new ExchangeRateP24(),
            new ExchangeRateMinfin(),
            new ExchangeRateMonobank()
    );
    private final ExchangeRateUseCase rateUseCase;

    public void getExchangeRate() {
        rateUseCase.getExchangeRate(new HashMap<>());
    }
}
