package ua.vholovetskyi.exchangerate.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.vholovetskyi.commons.utils.FormatterUtils;
import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;
import ua.vholovetskyi.exchangerate.application.port.ExchangeRateFetchUseCase;
import ua.vholovetskyi.exchangerate.db.ExchangeRateRepository;
import ua.vholovetskyi.exchangerate.domain.ExchangeRate;

import java.util.Date;
import java.util.List;

import static ua.vholovetskyi.commons.utils.FormatterUtils.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommandExchangeRateService {
    private final ExchangeRateRepository rateRepository;

    public void saveAll(List<ExchangeRateDto> exchangeRatesDto) {
        List<ExchangeRate> exchangeRate = exchangeRatesDto
                .stream()
                .map(ExchangeRateDto::toExchangeRate)
                .filter(er -> !er.isDefaultMoney())
                .toList();
        rateRepository.saveAll(exchangeRate);
        log.info("Save the exchange rate in the DB: " + getFullDateFormat().format(new Date()));
    }
}
