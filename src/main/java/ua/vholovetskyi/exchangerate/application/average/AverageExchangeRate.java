package ua.vholovetskyi.exchangerate.application.average;

import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Stream;

public class AverageExchangeRate implements Average {

    @Override
    public AverageDto calculate(List<ExchangeRateDto> exchangeRate) {
        if (exchangeRate != null && !exchangeRate.isEmpty()) {
            final var size = exchangeRate.size();
            final var rateBuy = exchangeRate.stream().map(e -> e.amount().getRateBuy());
            final var rateSell = exchangeRate.stream().map(e -> e.amount().getRateSell());
            return new AverageDto(getAverage(size, rateBuy), getAverage(size, rateSell));
        }
        return new AverageDto(BigDecimal.ZERO, BigDecimal.ZERO);
    }

    private BigDecimal getAverage(int count, Stream<BigDecimal> rate) {
        final var sum = rate.reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(BigDecimal.valueOf(count), RoundingMode.HALF_EVEN);
    }
}
