package com.vasyl.medetskyy.command;

import com.vasyl.medetskyy.ExpenseRecord;
import com.vasyl.medetskyy.MoneyTracker;

import java.util.List;
import java.util.Map;

/**
 * Created by Laden on 03.06.2017.
 */
public class ListCommand implements Command {
    private MoneyTracker moneyTracker;

    public ListCommand(MoneyTracker moneyTracker) {
        this.moneyTracker = moneyTracker;
    }

    @Override
    public String run(String[] tokens) {
        Map<String, List<ExpenseRecord>> expenseRecordMap = moneyTracker.list();

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
