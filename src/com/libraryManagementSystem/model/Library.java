package com.libraryManagementSystem.model;


import com.libraryManagementSystem.notification.Notifier;
import com.libraryManagementSystem.strategy.SearchStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class Library {
    private final Map<String, Book> inventory = new HashMap<>();
    private final Map<Integer, Patron> patrons = new HashMap<>();
    private final Map<String, List<Patron>> reservations = new HashMap<>();

    private final Notifier notifier;
    private SearchStrategy searchStrategy;

    public Library(Notifier notifier) {
        this.notifier = notifier;
    }

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public void addBook(Book book) {
        if (inventory.containsKey(book.getIsbn())) {
            System.out.println("Error: Book with ISBN " + book.getIsbn() + " already exists.");
            return;
        }
        inventory.put(book.getIsbn(), book);
        System.out.println("Added book: " + book.getTitle());
    }

    public boolean removeBook(String isbn) {
        if (inventory.containsKey(isbn)) {
            List<Patron> queue = reservations.get(isbn);
            if (queue != null && !queue.isEmpty()) {
                System.out.println("Cannot remove book: Pending reservations exist.");
                return false;
            }
            inventory.remove(isbn);
            System.out.println("Removed book with ISBN: " + isbn);
            return true;
        }
        return false;
    }

    public List<Book> searchBook(String query) {
        if (searchStrategy == null) {
            System.out.println("Error: Search strategy not set.");
            return new ArrayList<Book>();
        }
        return searchStrategy.search(this.inventory, query);
    }

    public void addPatron(Patron patron) {
        if (patrons.containsKey(patron.getPatronId())) {
            System.out.println("Error: Patron with ID " + patron.getPatronId() + " already exists.");
            return;
        }
        patrons.put(patron.getPatronId(), patron);
        System.out.println("Added new patron: " + patron.getName());
    }

    public List<String> getPatronHistory(int patronId) {
        Patron patron = patrons.get(patronId);
        return patron != null ? patron.getBorrowingHistory() : new ArrayList<String>();
    }

    public boolean checkoutBook(int patronId, String isbn) {
        Patron patron = patrons.get(patronId);
        Book book = inventory.get(isbn);

        if (patron == null || book == null) {
            System.out.println("Checkout failed: Invalid Patron ID or Book ISBN.");
            return false;
        }

        if (book.borrow()) {
            patron.addBorrowedBook(isbn);
            System.out.println("Checkout successful: " + book.getTitle() + " for " + patron.getName());
            return true;
        } else {
            List<Patron> queue = reservations.get(isbn);
            boolean alreadyReserved = (queue != null && queue.contains(patron));

            if (alreadyReserved) {
                System.out.println("Checkout failed: Book is already reserved by this patron or checked out.");
            } else {
                if (queue == null) {
                    queue = new ArrayList<>();
                    reservations.put(isbn, queue);
                }
                queue.add(patron);
                patron.addReservation(book);
                System.out.println("Book unavailable. " + patron.getName() + " has been added to the reservation list.");
                notifier.notify(patron, "You have successfully reserved the book: " + book.getTitle());
            }
            return false;
        }
    }

    public boolean returnBook(int patronId, String isbn) {
        Patron patron = patrons.get(patronId);
        Book book = inventory.get(isbn);

        if (patron == null || book == null) {
            System.out.println("Return failed: Invalid Patron ID or Book ISBN.");
            return false;
        }

        book.returnBook();
        System.out.println("Return successful: " + book.getTitle() + " returned by " + patron.getName());

        List<Patron> queue = reservations.get(isbn);
        if (queue != null && !queue.isEmpty()) {
            Patron nextPatron = queue.remove(0);
            nextPatron.removeReservation(book);
            notifier.notify(nextPatron, "Your reserved book, " + book.getTitle() + ", is now available for checkout!");
            System.out.println("Notified " + nextPatron.getName() + " that the book is ready.");
            if (queue.isEmpty()) {
                reservations.remove(isbn);
            }
        }
        return true;
    }
}
