package starter.Service;

import org.springframework.stereotype.Service;
import starter.dto.CsvValidData;

import java.util.List;


@Service
public interface LoadFileService {
    List<String> execute();
}
