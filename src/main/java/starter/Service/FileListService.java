package starter.Service;

import org.springframework.stereotype.Service;
import starter.dto.CsvValidData;

import java.util.List;

@Service
public interface FileListService {
    List<CsvValidData> execute(String filename);
}
