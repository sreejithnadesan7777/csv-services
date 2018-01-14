package starter.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import starter.Service.CsvExtractServiceImpl;
import starter.controller.CsvServiceController;

@ControllerAdvice(assignableTypes = {CsvServiceController.class})
public class CsvServiceControllerExceptionHandler {

  @Autowired
  private CsvExtractServiceImpl csvExtractServiceImpl;

  private static final Logger logger = LoggerFactory.getLogger("csv.logger");

  @ExceptionHandler
  public ResponseEntity<String> handleExceptions(Exception exception) {
    logger.error("Exception = ", exception);
    String response = "{\"statusCode\":\"ERROR\",\"description\":\"OPERATION FAILED\"}";
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
