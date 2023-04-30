package ua.vholovetskyi.exchangerate.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.vholovetskyi.commons.utils.FormatterUtils;
import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;
import ua.vholovetskyi.exchangerate.application.port.ExchangeRateUseCase;
import ua.vholovetskyi.exchangerate.db.ExchangeRateRepository;
import ua.vholovetskyi.exchangerate.domain.ExchangeRate;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommandExchangeRateService {
    private final ExchangeRateUseCase exchangeRateUseCase;
    private final ExchangeRateRepository rateRepository;

    public void saveExchangeRate() {
        List<ExchangeRate> exchangeRates = exchangeRateUseCase
                .getExchangeRate()
                .stream()
                .map(ExchangeRateDto::toExchangeRate)
                .filter(er -> !er.isDefaultMoney())
                .toList();
        rateRepository.saveAll(exchangeRates);
        log.info("Save the exchange rate in the DB: " + FormatterUtils.getFullDateFormat().format(new Date()));
    }
}
