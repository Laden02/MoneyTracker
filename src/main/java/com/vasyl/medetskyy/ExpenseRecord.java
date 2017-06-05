package com.vasyl.medetskyy;

import java.util.Date;

/**
 * Created by Laden on 03.06.2017.
 */
public class ExpenseRecord {
    private Integer id;
    private String date;
    private Double amount;
    private String currency;
    private String name;

    public ExpenseRecord(Integer id, String date, Double amount, String currency, String name) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpenseRecord that = (ExpenseRecord) o;

        if (!id.equals(that.id)) return false;
        if (!date.equals(that.date)) return false;
        if (!amount.equals(that.amount)) return false;
        if (!currency.equals(that.currency)) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + currency.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ExpenseRecord{" +
                "date='" + date + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
