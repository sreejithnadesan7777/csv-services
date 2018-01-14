package starter.dataaccess;

import org.springframework.data.repository.CrudRepository;
import starter.dvo.CsvCurrencyExchangeCount;

public interface CsvCurrencyExchangeCountRepository extends CrudRepository<CsvCurrencyExchangeCount, Long> {
}
