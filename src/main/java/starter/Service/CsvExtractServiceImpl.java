package starter.Service;


import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import starter.common.Validator;
import starter.dataaccess.CsvCurrencyExchangeCountRepository;
import starter.dto.CsvInvalidData;
import starter.dto.CsvValidData;
import starter.dvo.CsvCurrencyExchangeCount;
import starter.dvo.ForignExchange;
import starter.dvo.Response;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static starter.common.CsvServiceContstants.EXISTS_DESC;
import static starter.common.CsvServiceContstants.EXISTS_STATUS_CODE;
import static starter.common.CsvServiceContstants.FILE_UPLOAD_SERVICE;
import static starter.common.CsvServiceContstants.INVALID_STATUS_CODE;
import static starter.common.CsvServiceContstants.INVALID_STATUS_DESC;
import static starter.common.CsvServiceContstants.OPERATION_END_TIME;
import static starter.common.CsvServiceContstants.OPERATION_START_TIME;
import static starter.common.CsvServiceContstants.PATH;
import static starter.common.CsvServiceContstants.RESPONSE;
import static starter.common.CsvServiceContstants.SERVICE_NAME;
import static starter.common.CsvServiceContstants.SOURCE_FILE;
import static starter.common.CsvServiceContstants.SUCCESS_DESC;
import static starter.common.CsvServiceContstants.SUCCESS_STATUS_CODE;


@Component
public class CsvExtractServiceImpl implements CsvExtractService {
  @Autowired
  private starter.dataaccess.CsvInvalidDataRepository csvInvalidDataRepository;

  @Autowired
  private starter.dataaccess.CsvValidDataRepository csvValidDataRepository;

  @Autowired
  private CsvCurrencyExchangeCountRepository csvCurrencyExchangeCountRepository;

  private static final Logger logger = LoggerFactory.getLogger("csv.logger");

  private static Date startTime;
  private static Date endTime;

  @Override
  public Response execute(MultipartFile file) throws Exception {
    startTime = new java.util.Date();
    Response response = null;
    long validCount = 0L;
    long invalidCount = 0L;
    String filename = file.getOriginalFilename();
    String statusCode;
    String description;
    if (validateFilename(filename)) {
      fileUpload(file);
      logger.info(SERVICE_NAME + FILE_UPLOAD_SERVICE);
      logger.info(OPERATION_START_TIME + startTime);
      logger.info(SOURCE_FILE + filename);
      if (CollectionUtils.isEmpty(csvValidDataRepository.findByCsvOrderFilename(filename))) {
        List<ForignExchange> forignExchangeList = getforignExchangeList(filename);
        List<ForignExchange> getValidCsvList = Validator.getValidCsvList(forignExchangeList);
        forignExchangeList.removeAll(getValidCsvList);
        //Persisting invalid datas
        csvInvalidDataRepository.save(populateCsvInvalidData(forignExchangeList, filename));
        invalidCount = forignExchangeList.size();
        //persisting valid datas
        csvValidDataRepository.save(populateCsvValidData(getValidCsvList, filename));
        validCount = getValidCsvList.size();
        List<CsvCurrencyExchangeCount> currencyExchangeCountList = csvValidDataRepository.findCurrencyCount();
        csvCurrencyExchangeCountRepository.deleteAll();
        //saving the ordering currency and count
        csvCurrencyExchangeCountRepository.save(currencyExchangeCountList);
        statusCode = SUCCESS_STATUS_CODE;
        description = SUCCESS_DESC;
      } else {
        statusCode = EXISTS_STATUS_CODE;
        description = EXISTS_DESC;
      }
    } else {
      statusCode = INVALID_STATUS_CODE;
      description = INVALID_STATUS_DESC;
    }
    response = buildResponse(statusCode, description, validCount, invalidCount);
    logger.info(RESPONSE + response);

    return response;
  }

  private List<ForignExchange> getforignExchangeList(String filename) throws Exception {
    List<ForignExchange> list = new ArrayList<>();

    ColumnPositionMappingStrategy strat = new ColumnPositionMappingStrategy();
    strat.setType(ForignExchange.class);
    String[] columns = new String[]{"id", "orderCurrency", "orderingCurrency", "date", "amount"};
    strat.setColumnMapping(columns);
    CsvToBean csv = new CsvToBean();
    CSVReader csvReader = new CSVReader(new FileReader(PATH+filename));
    list = csv.parse(strat, csvReader);
    return list;
  }

  private List<CsvInvalidData> populateCsvInvalidData(List<ForignExchange> forignExchangeList, String filename) {
    return forignExchangeList.stream()
            .map(e -> new CsvInvalidData(e.getId(), e.getOrderCurrency(), e.getOrderingCurrency(), e.getDate(), e.getAmount(), filename))
            .collect(Collectors.toList());
  }

  private List<CsvValidData> populateCsvValidData(List<ForignExchange> forignExchangeList, String filename) {
    return forignExchangeList.stream()
            .map(e -> new CsvValidData(e.getId(), e.getOrderCurrency(), e.getOrderingCurrency(), e.getDate(), e.getAmount(), filename))
            .collect(Collectors.toList());
  }

  private Response buildResponse(String statusCode, String description, long validCount, long invalidCount) throws Exception {
    Response response = new Response();
    response.setStatusCode(statusCode);
    response.setDescription(description);
    response.setValidCount(validCount);
    response.setInvalidCount(invalidCount);
    response.setCountStatus(true);
    endTime = new Date();
    response.setProcessTime(String.valueOf(getProcessTime())+" Sec");
    logger.info(OPERATION_END_TIME + endTime + '\n');
    return response;
  }

  public void fileUpload(MultipartFile file) throws Exception{
    byte[] bytes = file.getBytes();
    Path path = Paths.get(PATH + file.getOriginalFilename());
    Files.write(path, bytes);
  }

  private long getProcessTime() {
    return ((endTime.getTime()-startTime.getTime())/1000);
  }

  private boolean validateFilename(String fileName) {
    String[] tokens = fileName.split("\\.(?=[^\\.]+$)");
    if (!tokens[1].equalsIgnoreCase("csv")) {
      return false;
    }
    return true;
  }


}
