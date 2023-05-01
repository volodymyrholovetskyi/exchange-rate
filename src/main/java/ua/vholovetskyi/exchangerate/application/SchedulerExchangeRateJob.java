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
class SchedulerExchangeRateJob {
    private final CommandExchangeRateService rateService;

    //run every day at 5 am
    @Scheduled(cron = "${exchange-rate.scheduler.process.cron}")
    public void run() {
        log.info("Run scheduler: " + FormatterUtils.getFullDateFormat().format(new Date()));
        rateService.saveExchangeRate();
    }
}
