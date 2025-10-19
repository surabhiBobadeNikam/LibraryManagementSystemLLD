package com.libraryManagementSystem.strategy;

import com.libraryManagementSystem.model.Book;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ISBNSearchStrategy implements SearchStrategy {
    @Override
    public List<Book> search(Map<String, Book> inventory, String query) {
        Book book = inventory.get(query);
        if (book != null) {
            return Collections.singletonList(book);
        }
        return Collections.emptyList();
    }
}
