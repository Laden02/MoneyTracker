package com.vasyl.medetskyy;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class MoneyTracker {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "zasada00";
    private static final String URL = "jdbc:mysql://localhost:3306/MONEY_TRACKER?serverTimezone=UTC&useSSL=false";


    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot init the application");
            throw new MoneyTrackerException("Cannot init the application", e);
        }
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Cannot get connection");
            throw new MoneyTrackerException("Cannot get connection", e);
        }
    }

    public Map<String, List<ExpenseRecord>> add(String dateOfExpense, String amount, String currency, String nameOfProduct) {
        try (Connection connection = getConnection();
             PreparedStatement stm = connection.prepareStatement("INSERT INTO MONEY_TRACKER(DATE_OF_EXPENSE, AMOUNT, " +
                     "CURRENCY, NAME_OF_PRODUCT) VALUES(?, ?, ?, ?)")) {

            stm.setString(1, dateOfExpense);
            stm.setDouble(2, Double.parseDouble(amount));
            stm.setString(3, currency);
            stm.setString(4, nameOfProduct);

            stm.execute();
        } catch (SQLException e) {
            System.out.printf("Error during db communication " + e.getMessage());
            throw new MoneyTrackerException("Error during db communication", e);
        }

        return list();
    }

    public Map<String, List<ExpenseRecord>> clear(String dateOfExpense) {
        try (Connection connection = getConnection();
             PreparedStatement stm = connection.prepareStatement("DELETE FROM MONEY_TRACKER WHERE DATE_OF_EXPENSE = ?")) {

            stm.setString(1, dateOfExpense);

            stm.execute();
        } catch (SQLException e) {
            System.out.printf("Error during db communication " + e.getMessage());
            throw new MoneyTrackerException("Error during db communication", e);
        }

        return list();
    }

    public Map<String, List<ExpenseRecord>> list() {
        try (Connection connection = getConnection();
             Statement stm = connection.createStatement();
             ResultSet resultSet = stm.executeQuery("SELECT ID, DATE_OF_EXPENSE, AMOUNT, CURRENCY, NAME_OF_PRODUCT "
                     + "FROM MONEY_TRACKER ORDER BY DATE_OF_EXPENSE ASC ")) {

            Map<String, List<ExpenseRecord>> map = new LinkedHashMap<>();
            while (resultSet.next()) {
                ExpenseRecord expenseRecord = new ExpenseRecord(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getDouble(3), resultSet.getString(4), resultSet.getString(5));
                if (map.containsKey(expenseRecord.getDate())) {
                    List<ExpenseRecord> records = map.get(expenseRecord.getDate());
                    records.add(expenseRecord);
                } else {
                    List<ExpenseRecord> records = new ArrayList<>();
                    records.add(expenseRecord);
                    map.put(expenseRecord.getDate(), records);
                }
            }
            return map;
        } catch (SQLException e) {
            System.out.printf("Error during db communication " + e.getMessage());
            throw new MoneyTrackerException("Error during db communication", e);
        }
    }


    public Map<String, Double> total(){
        try (Connection connection = getConnection();
             Statement stm = connection.createStatement();
             ResultSet resultSet = stm.executeQuery("SELECT CURRENCY, SUM(AMOUNT) FROM MONEY_TRACKER GROUP BY CURRENCY")) {

            Map<String, Double> map = new LinkedHashMap<>();
            while (resultSet.next()) {
                map.put(resultSet.getString(1), resultSet.getDouble(2));
            }
            return map;
        } catch (SQLException e) {
            System.out.printf("Error during db communication " + e.getMessage());
            throw new MoneyTrackerException("Error during db communication", e);
        }
    }
}




