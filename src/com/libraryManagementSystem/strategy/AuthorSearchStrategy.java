package com.libraryManagementSystem.strategy;

import com.libraryManagementSystem.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AuthorSearchStrategy implements SearchStrategy {
    @Override
    public List<Book> search(Map<String, Book> inventory, String query) {
        List<Book> results = new ArrayList<>();
        String lowerCaseQuery = query.toLowerCase();

        for (Book book : inventory.values()) {
            if (book.getAuthor().toLowerCase().contains(lowerCaseQuery)) {
                results.add(book);
            }
        }
        return results;
    }
}
