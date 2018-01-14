package starter.dvo;


public class ForignExchange {
  private String id;
  private String orderCurrency;
  private String orderingCurrency;
  private String date;
  private String amount;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getOrderCurrency() {
    return orderCurrency;
  }

  public void setOrderCurrency(String orderCurrency) {
    this.orderCurrency = orderCurrency;
  }

  public String getOrderingCurrency() {
    return orderingCurrency;
  }

  public void setOrderingCurrency(String orderingCurrency) {
    this.orderingCurrency = orderingCurrency;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }
}
