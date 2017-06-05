package com.vasyl.medetskyy;

/**
 * Created by Laden on 03.06.2017.
 */
public class MoneyTrackerException extends RuntimeException {
    public MoneyTrackerException(String message) {
        super(message);
    }

    public MoneyTrackerException(String message, Throwable cause) {
        super(message, cause);
    }
}
