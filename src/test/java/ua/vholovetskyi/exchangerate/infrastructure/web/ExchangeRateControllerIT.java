package ua.vholovetskyi.exchangerate.infrastructure.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ua.vholovetskyi.exchangerate.BaseExchangerRateTest;
import ua.vholovetskyi.exchangerate.application.CommandExchangeRateService;
import ua.vholovetskyi.exchangerate.application.dto.RichExchangeRateDto;
import ua.vholovetskyi.exchangerate.db.ExchangeRateRepository;
import ua.vholovetskyi.exchangerate.domain.Bank;

import java.util.Map;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase
class ExchangeRateControllerIT extends BaseExchangerRateTest {

    @Autowired
    ExchangeRateRepository rateRepository;

    @Autowired
    CommandExchangeRateService commandExchangeRate;

    @Autowired
    ExchangeRateController rateController;

    @Test
    void get_all_exchange_rate() {
        //given
        commandExchangeRate.saveAll(givenListPrivate24ExchangeRate());

        //when
        Map<Bank, Map<String, RichExchangeRateDto>> all = rateController.getAll(Optional.empty(), Optional.empty());

        //then
        assertEquals(1, all.size());
    }



}