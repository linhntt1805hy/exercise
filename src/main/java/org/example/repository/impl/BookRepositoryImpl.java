package org.example.repository.impl;

import org.example.Main;
import org.example.entity.Book;
import org.example.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @Override
    public Book saveBook(Book book) {
        Main.books.add(book);
        return book;
    }

    @Override
    public List<Book> getAllBook() {
        return Main.books;
    }
}
