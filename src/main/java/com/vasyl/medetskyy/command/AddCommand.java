package com.vasyl.medetskyy.command;

import com.vasyl.medetskyy.ExpenseRecord;
import com.vasyl.medetskyy.InputValidator;
import com.vasyl.medetskyy.MoneyTracker;

import java.util.List;
import java.util.Map;

/**
 * Created by Laden on 03.06.2017.
 */
public class AddCommand implements Command {
    private InputValidator inputValidator;
    private MoneyTracker moneyTracker;

    public AddCommand(InputValidator inputValidator, MoneyTracker moneyTracker) {
        this.inputValidator = inputValidator;
        this.moneyTracker = moneyTracker;
    }

    @Override
    public String run(String[] tokens) {
        String date = tokens[1];
        String amount = tokens[2];
        String currency = tokens[3];
        String name = tokens[4];

        inputValidator.validateAmount(amount);
        inputValidator.validateCurrency(currency);
        inputValidator.validateDate(date);

        Map<String, List<ExpenseRecord>> expenseRecordMap = moneyTracker.add(date, amount, currency, name);

        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, List<ExpenseRecord>> entry : expenseRecordMap.entrySet()) {
            builder.append(entry.getKey());
            builder.append("\n");
            for (ExpenseRecord expenseRecord : entry.getValue()) {
                builder.append(String.format("%s %.2f %s", expenseRecord.getName(), expenseRecord.getAmount(),
                        expenseRecord.getCurrency()));
                builder.append("\n");
            }

            builder.append("\n");
        }

        return builder.toString();
    }
}
