package ua.vholovetskyi.exchangerate.application.port;

import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;

import java.util.List;

public interface ExchangeRateUseCase {

    List<ExchangeRateDto> getExchangeRate();
}
