package ua.vholovetskyi.exchangerate.application.average;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AverageDto {

    private BigDecimal averageRateBuy;
    private BigDecimal averageRateSell;
}
