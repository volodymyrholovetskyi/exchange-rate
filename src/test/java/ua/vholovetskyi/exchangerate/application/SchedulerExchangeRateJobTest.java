package ua.vholovetskyi.exchangerate.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
class SchedulerExchangeRateJobTest {

    @Autowired
    SchedulerExchangeRateJob exchangeRateJob;

    @Test
    void should_return_exchange_rate_taken() {
        //given

        //when
        exchangeRateJob.run();
        //then
    }

}