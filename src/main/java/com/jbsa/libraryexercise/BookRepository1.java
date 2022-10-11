package com.jbsa.libraryexercise;

import org.springframework.stereotype.Repository;

@Repository

public interface BookRepository1  {
    Book retrieveBook(String bookReference);
    boolean existsByReference(String bookReference);

//    boolean existsByReference(String reference);
//
//    @Query("SELECT b FROM Book b where b.reference = :reference")
//    Book retrieveBook(@Param("reference") String reference);
}
