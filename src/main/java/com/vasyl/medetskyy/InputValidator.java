package com.vasyl.medetskyy;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by Laden on 03.06.2017.
 */
public class InputValidator {
    private final String DATE_REGEXP = "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
    private Pattern pattern;
    private Set<String> allowedCurrency;

    public InputValidator() {
        pattern = Pattern.compile(DATE_REGEXP);
        allowedCurrency = new HashSet<>();
        allowedCurrency.add("AUD");
        allowedCurrency.add("EUR");
        allowedCurrency.add("BGN");
        allowedCurrency.add("BRL");
        allowedCurrency.add("CAD");
        allowedCurrency.add("CHF");
        allowedCurrency.add("CNY");
        allowedCurrency.add("CZK");
        allowedCurrency.add("DKK");
        allowedCurrency.add("GBP");
        allowedCurrency.add("HKD");
        allowedCurrency.add("HRK");
        allowedCurrency.add("HUF");
        allowedCurrency.add("IDR");
        allowedCurrency.add("ILS");
        allowedCurrency.add("INR");
        allowedCurrency.add("JPY");
        allowedCurrency.add("KRW");
        allowedCurrency.add("MXN");
        allowedCurrency.add("MYR");
        allowedCurrency.add("NOK");
        allowedCurrency.add("NZD");
        allowedCurrency.add("PHP");
        allowedCurrency.add("PLN");
        allowedCurrency.add("RON");
        allowedCurrency.add("RUB");
        allowedCurrency.add("SEK");
        allowedCurrency.add("SGD");
        allowedCurrency.add("THB");
        allowedCurrency.add("TRY");
        allowedCurrency.add("USD");
        allowedCurrency.add("ZAR");
    }

    public void validateAmount(String amount) {
        double amountDouble = 0.0;
        try {
            amountDouble = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            throw new ValidationException("Amount should be decimal");
        }
        if (amountDouble < 0) {
            throw new ValidationException("Amount should be bigger than 0");
        }
    }

    public void validateDate(String date) {
        if (!pattern.matcher(date).matches()) {
            throw new ValidationException("Invalid date format. Use yyyy-mm-dd");
        }
    }

    public void validateCurrency(String currency) {
        if (!allowedCurrency.contains(currency)) {
            throw new ValidationException("Not supported currency type " + currency);
        }
    }
}
