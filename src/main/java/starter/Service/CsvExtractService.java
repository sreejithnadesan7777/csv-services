package starter.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import starter.dvo.Response;

@Service
public interface CsvExtractService {
  Response execute(MultipartFile file) throws Exception;
}
