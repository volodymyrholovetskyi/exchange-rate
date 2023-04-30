package ua.vholovetskyi.exchangerate.application.port;

import ua.vholovetskyi.exchangerate.application.dto.RichExchangeRateDto;
import ua.vholovetskyi.exchangerate.domain.Bank;

import java.util.Map;

public interface QueryExchangeRateUseCase {

    Map<Bank, Map<String, RichExchangeRateDto>> findAll();

    Map<Bank, Map<String, RichExchangeRateDto>> findByPeriodFromTo(String from, String to);

}
