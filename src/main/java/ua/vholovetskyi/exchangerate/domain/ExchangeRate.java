package ua.vholovetskyi.exchangerate.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.vholovetskyi.commons.jpa.EntityBase;
import ua.vholovetskyi.exchangerate.application.dto.ExchangeRateDto;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ExchangeRate extends EntityBase {
    @Enumerated(EnumType.STRING)
    private Bank bank;
    @Embedded
    private Money amount;
    private LocalDate createAt;

    public boolean isDefaultMoney() {
        return amount.isDefaultMoney(amount);
    }

    public ExchangeRateDto toExchangeRateDto() {
        return new ExchangeRateDto(bank, amount, createAt);
    }
}
