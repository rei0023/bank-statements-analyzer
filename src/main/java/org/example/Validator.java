package org.example;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Validator {
    private String amount;
    private String date;
    private String description;

    public Validator(final String amount, final String date, final String description) {
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public Notification validate() {
        Notification notification = new Notification();
        if (this.description.length() > 100) {
            notification.addError("The description is too long");
        }

        final LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(this.date);
            if (parsedDate.isAfter(LocalDate.now())) {
                notification.addError("The date cannot be in the future");
            }
        } catch (DateTimeException ex) {
            notification.addError("Invalid format for date");
        }

        final double amount;
        try {
            amount = Double.parseDouble(this.amount);
        } catch (NumberFormatException ex) {
            notification.addError("Invalid format for amount");
        }
        return notification;
    }
}
