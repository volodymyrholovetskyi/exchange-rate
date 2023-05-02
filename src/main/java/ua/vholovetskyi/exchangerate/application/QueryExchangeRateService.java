package ua.vholovetskyi.exchangerate.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.vholovetskyi.exchangerate.application.average.AverageDto;
import ua.vholovetskyi.exchangerate.application.average.AverageService;
import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;
import ua.vholovetskyi.exchangerate.application.dto.RichExchangeRateDto;
import ua.vholovetskyi.exchangerate.application.port.QueryExchangeRateUseCase;
import ua.vholovetskyi.exchangerate.db.ExchangeRateRepository;
import ua.vholovetskyi.exchangerate.domain.Bank;
import ua.vholovetskyi.exchangerate.domain.ExchangeRate;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;
import static ua.vholovetskyi.commons.utils.FormatterUtils.toDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueryExchangeRateService implements QueryExchangeRateUseCase {

    private final ExchangeRateRepository rateRepository;
    private final AverageService averageService;

    @Override
    public Map<Bank, Map<String, RichExchangeRateDto>> findAll() {
        List<ExchangeRate> exchangeRates = rateRepository.findAll();
        return toRichExchangeRate(exchangeRates);
    }

    @Override
    public Map<Bank, Map<String, RichExchangeRateDto>> findByPeriodFromTo(String from, String to) {
        final var exchangeRates = rateRepository.findByCreateAtBetween(toDate(from), toDate(to));
        return toRichExchangeRate(exchangeRates);
    }

    private Map<Bank, Map<String, RichExchangeRateDto>> toRichExchangeRate(List<ExchangeRate> exchangeRates) {
        Map<Bank, Map<String, RichExchangeRateDto>> richExchangeRates = exchangeRates
                .stream()
                .map(ExchangeRate::toExchangeRateDto)
                .collect(groupingBy(ExchangeRateDto::bank, groupingBy(e -> e.amount().fullCurrencyCode(), collectingAndThen(toList(), list -> {
                            AverageDto averageDto = averageService.calculateAverage(list);
                            return new RichExchangeRateDto(list, averageDto);
                        }
                ))));
        log.info("Obtaining the exchange rate from the database. Size: " + richExchangeRates.size());
        return richExchangeRates;
    }
}
