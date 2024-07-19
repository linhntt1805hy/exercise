package org.example.repository.impl;

import org.example.Main;
import org.example.entity.Author;
import org.example.repository.AuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    @Override
    public Author saveAuthor(Author author) {
        Main.authors.add(author);
        return author;
    }

    @Override
    public List<Author> getAllAuthor() {
        return Main.authors;
    }
}
