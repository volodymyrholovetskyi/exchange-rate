package ua.vholovetskyi.exchangerate.application.average;

import ua.vholovetskyi.exchangerate.application.port.dto.ExchangeRateDto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ExchangeRateAverImpl implements AverageStrategy {

    @Override
    public AverageDto calculate(List<ExchangeRateDto> exchangeRate) {
        return new AverageDto(
                getAverageRateBuy(exchangeRate),
                getAverageRateSell(exchangeRate)
        );
    }

    private BigDecimal getAverageRateBuy(List<ExchangeRateDto> exchangeRate) {
        final BigDecimal sum = exchangeRate.stream().map(e -> e.amount().getRateBuy()).reduce(BigDecimal.ZERO, BigDecimal::add);
        final long count = exchangeRate.stream().map(e -> e.amount().getRateBuy()).count();
        return sum.divide(BigDecimal.valueOf(count), RoundingMode.HALF_EVEN);
    }

    private BigDecimal getAverageRateSell(List<ExchangeRateDto> exchangeRate) {
        final BigDecimal sum = exchangeRate.stream().map(e -> e.amount().getRateSell()).reduce(BigDecimal.ZERO, BigDecimal::add);
        final long count = exchangeRate.stream().map(e -> e.amount().getRateSell()).count();
        return sum.divide(BigDecimal.valueOf(count), RoundingMode.HALF_EVEN);
    }
}
