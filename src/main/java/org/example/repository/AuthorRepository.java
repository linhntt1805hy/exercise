package org.example.repository;

import org.example.entity.Author;

import java.util.List;

public interface AuthorRepository {
    Author saveAuthor(Author author);
    List<Author> getAllAuthor();
}
