package com.vasyl.medetskyy;

import com.vasyl.medetskyy.command.AddCommand;
import com.vasyl.medetskyy.command.ClearCommand;
import com.vasyl.medetskyy.command.ListCommand;
import com.vasyl.medetskyy.command.TotalCommand;

import java.util.Scanner;

/**
 * Created by Laden on 03.06.2017.
 */
public class Main {

    public static void main(String[] args) {
        InputValidator inputValidator = new InputValidator();
        MoneyTracker moneyTracker = new MoneyTracker();
        moneyTracker.init();

        AddCommand addCommand = new AddCommand(inputValidator, moneyTracker);
        ListCommand listCommand = new ListCommand(moneyTracker);
        ClearCommand clearCommand = new ClearCommand(inputValidator, moneyTracker);
        TotalCommand totalCommand = new TotalCommand(moneyTracker, inputValidator, new ExchangeRateClient());

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String data = in.nextLine();
            String[] tokens = data.split(" ");

            try {

                if ("add".equals(tokens[0]) && tokens.length == 5) {
                    System.out.println(addCommand.run(tokens));
                } else if ("list".equals(tokens[0])) {
                    System.out.println(listCommand.run(tokens));
                } else if ("clear".equals(tokens[0]) && tokens.length == 2) {
                    System.out.println(clearCommand.run(tokens));
                } else if ("total".equals(tokens[0]) && tokens.length == 2) {
                    System.out.println(totalCommand.run(tokens));
                } else if ("exit".equals(tokens[0])) {
                    break;
                } else {
                    //// TODO: 03.06.2017  
                    System.out.println("Please try again.");
                }
            } catch (ValidationException | MoneyTrackerException e) {
                System.out.println("Internal error due to " + e.getMessage());
            }
        }
    }

}
