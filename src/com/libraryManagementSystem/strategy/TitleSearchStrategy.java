package com.libraryManagementSystem.strategy;

import com.libraryManagementSystem.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TitleSearchStrategy implements SearchStrategy{
    @Override
    public List<Book> search(Map<String, Book> inventory, String query) {
        List<Book> results = new ArrayList<Book>();
        String normalizedQuery = query.toLowerCase();
        for (Book book : inventory.values()) {
            if (book.getTitle().toLowerCase().contains(normalizedQuery)) {
                results.add(book);
            }
        }
        return results;
    }
}
