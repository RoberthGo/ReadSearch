package com.personal.project.readsearch;

import com.personal.project.readsearch.main.Menu;
import com.personal.project.readsearch.repository.IAuthorRepository;
import com.personal.project.readsearch.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReadSearchApplication implements CommandLineRunner {
    @Autowired
    private IAuthorRepository authorRepository;
    @Autowired
    private IBookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReadSearchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Menu menu = new Menu(bookRepository, authorRepository);
        menu.showMenu();
    }
}
