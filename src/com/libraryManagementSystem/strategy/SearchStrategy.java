package com.libraryManagementSystem.strategy;

import com.libraryManagementSystem.model.Book;

import java.util.List;
import java.util.Map;

public interface SearchStrategy {
    List<Book> search(Map<String, Book> inventory, String query);
}
