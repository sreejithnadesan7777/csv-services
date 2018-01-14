package starter.dto;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;


@Entity(name = "starter.dto.CsvInvalidData")
public class CsvInvalidData extends AbstractPersistable<Long> {
  private String csvOrderId;
  private String csvOrderCurrency;
  private String csvOrderingCurrency;
  private String csvOrderTimestamp;
  private String csvOrderAmount;
  private String csvOrderFilename;

  public CsvInvalidData(String csvOrderId, String csvOrderCurrency, String csvOrderingCurrency, String csvOrderTimestamp, String csvOrderAmount, String csvOrderFilename) {
    this.csvOrderId = csvOrderId;
    this.csvOrderCurrency = csvOrderCurrency;
    this.csvOrderingCurrency = csvOrderingCurrency;
    this.csvOrderTimestamp = csvOrderTimestamp;
    this.csvOrderAmount = csvOrderAmount;
    this.csvOrderFilename = csvOrderFilename;

  }


  public String getCsvOrderId() {
    return csvOrderId;
  }

  public void setCsvOrderId(String csvOrderId) {
    this.csvOrderId = csvOrderId;
  }

  public String getCsvOrderCurrency() {
    return csvOrderCurrency;
  }

  public void setCsvOrderCurrency(String csvOrderCurrency) {
    this.csvOrderCurrency = csvOrderCurrency;
  }

  public String getCsvOrderingCurrency() {
    return csvOrderingCurrency;
  }

  public void setCsvOrderingCurrency(String csvOrderingCurrency) {
    this.csvOrderingCurrency = csvOrderingCurrency;
  }

  public String getCsvOrderTimestamp() {
    return csvOrderTimestamp;
  }

  public void setCsvOrderTimestamp(String csvOrderTimestamp) {
    this.csvOrderTimestamp = csvOrderTimestamp;
  }

  public String getCsvOrderAmount() {
    return csvOrderAmount;
  }

  public void setCsvOrderAmount(String csvOrderAmount) {
    this.csvOrderAmount = csvOrderAmount;
  }

  public String getCsvOrderFilename() {
    return csvOrderFilename;
  }

  public void setCsvOrderFilename(String csvOrderFilename) {
    this.csvOrderFilename = csvOrderFilename;
  }
}
