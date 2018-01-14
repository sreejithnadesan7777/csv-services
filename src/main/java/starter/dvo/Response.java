package starter.dvo;


public class Response {
  private String statusCode;
  private String description;
  private Long validCount;
  private Long invalidCount;
  private boolean countStatus;
  public String processTime;
  public boolean isCountStatus() {
    return countStatus;
  }

  public void setCountStatus(boolean countStatus) {
    this.countStatus = countStatus;
  }


  public Long getValidCount() {
    return validCount;
  }

  public void setValidCount(Long validCount) {
    this.validCount = validCount;
  }

  public Long getInvalidCount() {
    return invalidCount;
  }

  public void setInvalidCount(Long invalidCount) {
    this.invalidCount = invalidCount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }

  public String getProcessTime() {
    return processTime;
  }

  public void setProcessTime(String processTime) {
    this.processTime = processTime;
  }
}
