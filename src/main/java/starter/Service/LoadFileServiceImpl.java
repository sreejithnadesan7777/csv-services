package starter.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class LoadFileServiceImpl implements LoadFileService {

    @Autowired
    private starter.dataaccess.CsvValidDataRepository csvValidDataRepository;

    @Override
    public List<String> execute() {
        return csvValidDataRepository.distinctCsvOrderFilename();
    }
}
