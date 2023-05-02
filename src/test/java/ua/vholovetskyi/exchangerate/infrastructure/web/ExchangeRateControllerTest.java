package ua.vholovetskyi.exchangerate.infrastructure.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.vholovetskyi.exchangerate.BaseExchangerRateTest;
import ua.vholovetskyi.exchangerate.application.dto.RichExchangeRateDto;
import ua.vholovetskyi.exchangerate.application.port.QueryExchangeRateUseCase;
import ua.vholovetskyi.exchangerate.domain.Bank;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ExchangeRateController.class})
class ExchangeRateControllerTest extends BaseExchangerRateTest {

    @MockBean
    QueryExchangeRateUseCase rateUseCase;

    @Autowired
    ExchangeRateController rateController;

    @Test
    void should_get_all_exchange_rate() {
        //given
        Mockito.when(rateUseCase.findAll()).thenReturn(givenMapRichExchangeRate());

        //when
        Map<Bank, Map<String, RichExchangeRateDto>> richExchangeRate = rateController.getAll(Optional.empty(), Optional.empty());

        //then
        assertEquals(1, richExchangeRate.size());
    }

}