package com.vasyl.medetskyy;

import java.util.Map;

/**
 * Created by Laden on 03.06.2017.
 */
public class FixerRate {
    private String base;
    private String date;
    private Map<String, Double> rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}
