package com.personal.project.readsearch.repository;

import com.personal.project.readsearch.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAll();
    List<Author> findAuthorByBirthYearGreaterThanEqualAndBirthYearLessThanEqual(int birthYear, int deadYear);
}
