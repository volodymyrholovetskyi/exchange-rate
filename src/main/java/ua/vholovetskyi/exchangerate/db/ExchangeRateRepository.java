package ua.vholovetskyi.exchangerate.db;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.vholovetskyi.exchangerate.domain.ExchangeRate;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    List<ExchangeRate> findByCreateAtBetween(LocalDate from, LocalDate to);
}
