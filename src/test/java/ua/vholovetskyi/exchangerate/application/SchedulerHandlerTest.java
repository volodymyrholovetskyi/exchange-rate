package ua.vholovetskyi.exchangerate.application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest(properties = "exchange-rate.scheduler.process.cron=* * * * * *")
class SchedulerHandlerTest {

    @SpyBean
    SchedulerHandler schedulerHandler;

    @Test
    void schedule_is_triggered() {
        await()
                .atMost(Duration.of(1500, ChronoUnit.MILLIS))
                .untilAsserted(() -> verify(schedulerHandler, atLeast(1)).run());
    }
}