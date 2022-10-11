package com.jbsa.libraryexercise;

import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository1  {
    Book retrieveBook(String bookReference);
    boolean existsByReference(String bookReference);
}
