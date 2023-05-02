package ua.vholovetskyi.exchangerate.infrastructure.web;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.vholovetskyi.exchangerate.BaseExchangerRateTest;
import ua.vholovetskyi.exchangerate.application.dto.RichExchangeRateDto;
import ua.vholovetskyi.exchangerate.application.port.QueryExchangeRateUseCase;
import ua.vholovetskyi.exchangerate.domain.Bank;

import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ExchangeRateControllerWebTest extends BaseExchangerRateTest {

    @MockBean
    QueryExchangeRateUseCase rateUseCase;

    @Autowired
    MockMvc mockMvc;

    @Test
    void should_get_all_exchange_rates() throws Exception {
        //given
        Map<Bank, Map<String, RichExchangeRateDto>> bankMap = givenMapRichExchangeRate();
        Mockito.when(rateUseCase.findAll()).thenReturn(bankMap);

        //expect
        mockMvc.perform(MockMvcRequestBuilders.get("/exchange/rate/average"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
