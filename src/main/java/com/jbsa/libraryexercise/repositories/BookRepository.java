package com.jbsa.libraryexercise.repositories;

import com.jbsa.libraryexercise.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository {
    Book retrieveBook(String bookReference);
    boolean existsByReference(String bookReference);
}
