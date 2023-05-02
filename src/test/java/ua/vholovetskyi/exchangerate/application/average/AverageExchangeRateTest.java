package ua.vholovetskyi.exchangerate.application.average;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.vholovetskyi.exchangerate.BaseExchangerRateTest;
import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;

import java.math.BigDecimal;
import java.util.List;

class AverageExchangeRateTest extends BaseExchangerRateTest {

    private AverageService averageService;

    @BeforeEach
    void stepUp() {
        this.averageService = new AverageService();
    }

    @Test
    void should_return_average_buy_exchange_rate() {
        //given
        List<ExchangeRateDto> listExchangeRate = givenListPrivate24ExchangeRate();

        //when
        AverageDto averageDto = averageService.calculateAverage(listExchangeRate);

        //then
        Assertions.assertEquals(BigDecimal.valueOf(37.51), averageDto.getAverageRateBuy());
    }

    @Test
    void should_return_average_sale_exchange_rate() {
        //given
        List<ExchangeRateDto> listExchangeRate = givenListPrivate24ExchangeRate();

        //when
        AverageDto averageDto = averageService.calculateAverage(listExchangeRate);

        //then
        Assertions.assertEquals(BigDecimal.valueOf(36.71), averageDto.getAverageRateSell());
    }

    @Test
    void should_return_zero_for_empty_list() {
        //given
        BigDecimal zero = BigDecimal.ZERO;
        List<ExchangeRateDto> emptyList = givenEmptyList();

        //when
        AverageDto averageDto = averageService.calculateAverage(emptyList);

        //then
        Assertions.assertEquals(zero, averageDto.getAverageRateBuy());
        Assertions.assertEquals(zero, averageDto.getAverageRateSell());
    }
}