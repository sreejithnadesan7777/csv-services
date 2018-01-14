package starter.dataaccess;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import starter.dto.CsvValidData;
import starter.dvo.CsvCurrencyExchangeCount;

import java.util.List;


public interface CsvValidDataRepository extends CrudRepository<CsvValidData, Long> {
  List<CsvValidData> findByCsvOrderFilename(String filename);

  @Query(value = "select distinct v.csvOrderFilename from starter.dto.CsvValidData v")
  List<String> distinctCsvOrderFilename();

  @Query(value = "select new starter.dvo.CsvCurrencyExchangeCount(v.csvOrderingCurrency, count(v)) from starter.dto.CsvValidData v group by v.csvOrderingCurrency")
  List<CsvCurrencyExchangeCount> findCurrencyCount();

}
