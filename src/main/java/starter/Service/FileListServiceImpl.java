package starter.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import starter.dto.CsvValidData;

import java.util.List;

@Component
public class FileListServiceImpl implements FileListService {
    @Autowired
    private starter.dataaccess.CsvValidDataRepository csvValidDataRepository;

    @Override
    public List<CsvValidData> execute(String filename) {
        return csvValidDataRepository.findByCsvOrderFilename(filename);
    }
}
