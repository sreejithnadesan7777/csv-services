package starter.dvo;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;


@Entity(name = "starter.dvo.CsvCurrencyExchangeCount")
public class CsvCurrencyExchangeCount extends AbstractPersistable<Long> {
  private String csvCurrency;
  private Long csvCount;

  public CsvCurrencyExchangeCount() {

  }

  public CsvCurrencyExchangeCount(String csvCurrency, Long csvCount) {
    this.csvCurrency = csvCurrency;
    this.csvCount = csvCount;
  }

  public String getCsvCurrency() {
    return csvCurrency;
  }

  public void setCsvCurrency(String csvCurrency) {
    this.csvCurrency = csvCurrency;
  }

  public Long getCsvCount() {
    return csvCount;
  }

  public void setCsvCount(Long csvCount) {
    this.csvCount = csvCount;
  }

}
