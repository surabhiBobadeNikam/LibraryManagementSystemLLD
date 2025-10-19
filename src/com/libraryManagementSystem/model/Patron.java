package com.libraryManagementSystem.model;

import java.util.ArrayList;
import java.util.List;

public class Patron {
    private final int patronId;
    private String name;
    private final List<String> borrowingHistory;
    private final List<Book> reservedBooks;

    public Patron(int patronId, String name) {
        this.patronId = patronId;
        this.name = name;
        this.borrowingHistory = new ArrayList<>();
        this.reservedBooks = new ArrayList<>();
    }

    public int getPatronId() { return patronId; }
    public String getName() { return name; }
    public List<String> getBorrowingHistory() { return new ArrayList<>(borrowingHistory); }
    public List<Book> getReservedBooks() { return reservedBooks; }

    public void setName(String name) { this.name = name; }

    public void addBorrowedBook(String isbn) {
        this.borrowingHistory.add(isbn);
    }

    public void addReservation(Book book) {
        if (!reservedBooks.contains(book)) {
            reservedBooks.add(book);
        }
    }

    public void removeReservation(Book book) {
        reservedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "Patron{" +
                "patronId=" + patronId +
                ", name='" + name + '\'' +
                ", borrowingHistory=" + borrowingHistory +
                ", reservedBooks=" + reservedBooks +
                '}';
    }
}
