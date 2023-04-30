package ua.vholovetskyi.exchangerate.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.vholovetskyi.exchangerate.application.average.AverageDto;
import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RichExchangeRate {
   private List<ExchangeRateDto> exchangeRates;
   private AverageDto average;
}
