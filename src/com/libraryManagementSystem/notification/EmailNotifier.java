package com.libraryManagementSystem.notification;

import com.libraryManagementSystem.model.Patron;

public class EmailNotifier implements Notifier {
    @Override
    public void notify(Patron patron, String message) {
        System.out.println(" [EMAIL NOTIFICATION] Sent to Patron ID " + patron.getPatronId() +
                " (" + patron.getName() + "): " + message);
    }
}
