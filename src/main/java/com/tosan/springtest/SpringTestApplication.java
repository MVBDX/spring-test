package com.tosan.springtest;

import com.tosan.springtest.entity.Author;
import com.tosan.springtest.entity.Book;
import com.tosan.springtest.repository.AuthorRepository;
import com.tosan.springtest.repository.BookRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTestApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(AuthorRepository authorRepository, BookRepository bookRepository) {
        return args -> {
            Author gabriel = authorRepository.save(new Author(null, "Gabriel Garcia Marquez", null));
            Author josh = authorRepository.save(new Author(null, "Josh brolin", null));

            bookRepository.saveAll(List.of(
                    new Book(null, "One hundred", "Orielly", gabriel),
                    new Book(null, "Two hundred", "Orielly 2", gabriel),
                    new Book(null, "Blindness", "Deitel", josh)
            ));
        };
    }

}
