package com.projects.neighborhoodLibrary.data;

import com.projects.neighborhoodLibrary.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Integer> {

    Book getBookByTitle(String title);
    Book getBookByIsbn(String isbn);
    Book checkout(int id, String name);
    void checkIn(int bookId);
}
