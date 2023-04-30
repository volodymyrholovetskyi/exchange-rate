package ua.vholovetskyi.exchangerate.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.vholovetskyi.commons.utils.FormatterUtils;

import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
class SchedulerExchangeRate {
    private final CommandExchangeRateService rateService;

    @Scheduled(cron = "0 1 * * * *")
    public void run() {
        log.info("Run scheduler: " + FormatterUtils.getFullDateFormat().format(new Date()));
        rateService.saveExchangeRate();
    }
}
