package com.libraryManagementSystem.notification;

import com.libraryManagementSystem.model.Patron;

public interface Notifier {
    void notify(Patron patron, String message);
}
