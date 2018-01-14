package starter.common;

import org.apache.commons.lang3.StringUtils;
import starter.dvo.ForignExchange;

import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class Validator {
    public static List<ForignExchange> getValidCsvList(List<ForignExchange> forignExchangeList) {
        //filtering valid data
        return forignExchangeList.stream().filter(p -> StringUtils.isNotEmpty(p.getId()))
                .filter(p -> StringUtils.isNotEmpty(p.getOrderCurrency()))
                .filter(p -> StringUtils.isNotEmpty(p.getOrderingCurrency()))
                .filter(p -> StringUtils.isNotEmpty(p.getDate()))
                .filter(p -> StringUtils.isNotEmpty(p.getAmount()))
                .filter(p -> isValidCurrencyCode(p.getOrderCurrency()))
                .filter(p -> isValidCurrencyCode(p.getOrderingCurrency())).collect(Collectors.toList());
    }

    //Method to check valid currency code
    public static boolean isValidCurrencyCode(String currencyCode) {
        try {
            Currency currency = Currency.getInstance(currencyCode);
            return Currency.getAvailableCurrencies().contains(currency);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
