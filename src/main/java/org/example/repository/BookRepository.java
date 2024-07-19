package org.example.repository;

import org.example.entity.Book;

import java.util.List;

public interface BookRepository {
    Book saveBook(Book book);
    List<Book> getAllBook();
}
