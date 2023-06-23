package com.tosan.springtest.controller;

import com.tosan.springtest.entity.Author;
import com.tosan.springtest.entity.Book;
import com.tosan.springtest.repository.AuthorRepository;
import com.tosan.springtest.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @QueryMapping
    Iterable<Author> authors() {
        return authorRepository.findAll();
    }

    @QueryMapping
    Optional<Author> authorById(@Argument Long id) {
        return authorRepository.findById(id);
    }

    @MutationMapping
    Book addBook(@Argument BookInput book) {
        Author author = authorRepository.findById(book.authorId).orElseThrow(() -> new IllegalArgumentException("author not found"));
        Book b = new Book(null, book.title, book.publisher, author);
        return bookRepository.save(b);
    }

    record BookInput(String title, String publisher, Long authorId) {
    }
}
