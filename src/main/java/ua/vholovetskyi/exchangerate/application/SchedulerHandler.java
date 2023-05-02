package ua.vholovetskyi.exchangerate.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.vholovetskyi.commons.utils.FormatterUtils;
import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;
import ua.vholovetskyi.exchangerate.application.port.ExchangeRateFetchUseCase;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
class SchedulerHandler {
    private final CommandExchangeRateService rateService;

    private final ExchangeRateFetchUseCase exchangeRateFetchUseCase;

    //run every day at 5 am
    @Scheduled(cron = "${exchange-rate.scheduler.process.cron}")
    public void run() {
        log.info("Run scheduler: " + FormatterUtils.getFullDateFormat().format(new Date()));
        List<ExchangeRateDto> exchangeRates = exchangeRateFetchUseCase.fetchExchangeRates();
        rateService.saveAll(exchangeRates);
    }
}
