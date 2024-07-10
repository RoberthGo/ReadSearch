package com.personal.project.readsearch.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private int birthYear;
    @Column(nullable = true)
    private int deathYear;
    private String name;
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(AuthorData author) {
        this.birthYear = author.birthYear();
        this.deathYear = author.deathYear();
        this.name = author.name();
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n"
                + "Birth Year: " + birthYear + "\n"
                + "Death Year: " + deathYear + "\n";
    }
}
