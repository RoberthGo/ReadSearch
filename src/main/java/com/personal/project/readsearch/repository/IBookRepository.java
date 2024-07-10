package com.personal.project.readsearch.repository;

import com.personal.project.readsearch.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IBookRepository extends JpaRepository<Book,Long> {
    List<Book> findAll();
    List<Book> findByLanguagesIsLikeIgnoreCase(String s);
    boolean existsByTitle(String title);
}
