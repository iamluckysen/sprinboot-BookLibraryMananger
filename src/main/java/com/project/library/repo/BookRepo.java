package com.project.library.repo;

import com.project.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM  Book b WHERE " +
    "LOWER(b.bookName) LIKE  lower(concat('%', :keyword, '%')) or " +
            "LOWER(b.bookAuthor) LIKE  lower(concat('%', :keyword, '%')) or " +
            "LOWER(b.bookCategory) LIKE  lower(concat('%', :keyword, '%')) ")
    List<Book> findByKeyword(@Param("keyword") String keyword);
}
