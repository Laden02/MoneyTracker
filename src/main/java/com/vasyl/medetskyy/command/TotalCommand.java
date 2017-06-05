package com.vasyl.medetskyy.command;

import com.vasyl.medetskyy.ExchangeRateClient;
import com.vasyl.medetskyy.InputValidator;
import com.vasyl.medetskyy.MoneyTracker;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Laden on 03.06.2017.
 */
public class TotalCommand implements Command{
    private MoneyTracker moneyTracker;
    private InputValidator inputValidator;
    private ExchangeRateClient exchangeRateClient;

    public TotalCommand(MoneyTracker moneyTracker, InputValidator inputValidator, ExchangeRateClient exchangeRateClient) {
        this.moneyTracker = moneyTracker;
        this.inputValidator = inputValidator;
        this.exchangeRateClient = exchangeRateClient;
    }

    @Override
    public String run(String[] tokens) {
        String currency = tokens[1];
        inputValidator.validateCurrency(currency);
        Map<String, Double> amounts = moneyTracker.total();

        List<String> neededRates = amounts.keySet().stream().collect(Collectors.toList());
        Map<String, Double> rates = exchangeRateClient.getRates(currency, neededRates);

        double sum = 0;

        for (Map.Entry<String, Double> entry : amounts.entrySet()) {
            String c = entry.getKey();
            if (!c.equals(currency)) {
                Double rate = rates.get(c);
                sum +=(entry.getValue() / rate);
            } else {
                sum += entry.getValue();
            }
        }

        return String.format("%.2f %s\n", sum, currency);
    }
}
