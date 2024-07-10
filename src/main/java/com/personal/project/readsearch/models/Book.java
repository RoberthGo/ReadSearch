package com.personal.project.readsearch.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @ManyToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Author> authors = new ArrayList<>();

    private String languages;
    private Long download_count;

    public Book(){
    }

    public Book(BookData book) {
        this.title = book.title();
        this.authors = book.authors().stream().map(e->new Author(e)).collect(Collectors.toList());
        this.languages = book.languages().stream().map(e->e.toString()).collect(Collectors.joining(","));
        this.download_count = book.downloadCount();
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public Long getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Long download_count) {
        this.download_count = download_count;
    }

    @Override
    public String toString() {
        String authorString = authors.stream()
                .map(e->e.toString())
                .collect(Collectors.joining("\n"));
        return "Book: " + title + "\n"
                + "Download Count: " + download_count + "\n"
                + "Authors: \n" + authorString
                + "Languages: " + languages + "\n";
    }
}
