package com.libraryManagementSystem.model;

public class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private final int publicationYear;
    private boolean isAvailable;

    public Book(String title, String author, String isbn, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.isAvailable = true;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getPublicationYear() { return publicationYear; }
    public boolean isAvailable() { return isAvailable; }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public boolean borrow() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    public void returnBook() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationYear=" + publicationYear +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
