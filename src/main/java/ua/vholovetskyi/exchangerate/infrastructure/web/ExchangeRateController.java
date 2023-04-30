package ua.vholovetskyi.exchangerate.infrastructure.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.vholovetskyi.exchangerate.application.dto.RichExchangeRateDto;
import ua.vholovetskyi.exchangerate.application.port.QueryExchangeRateUseCase;
import ua.vholovetskyi.exchangerate.domain.Bank;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/exchange-rate")
@RequiredArgsConstructor
public class ExchangeRateController {
    private final QueryExchangeRateUseCase queryCurrency;

    @GetMapping("/average")
    @ResponseStatus(HttpStatus.OK)
    public Map<Bank, Map<String, RichExchangeRateDto>> getAll(@RequestParam Optional<String> from, @RequestParam Optional<String> to) {
        if (from.isPresent() && to.isPresent()) {
            return queryCurrency.findByPeriodFromTo(from.get(), to.get());
        }
        return queryCurrency.findAll();
    }
}
