package starter.dataaccess;

import org.springframework.data.repository.CrudRepository;
import starter.dto.CsvInvalidData;

import java.util.List;


public interface CsvInvalidDataRepository extends CrudRepository<CsvInvalidData, Long> {
  List<CsvInvalidData> findByCsvOrderFilename(String filename);
}
