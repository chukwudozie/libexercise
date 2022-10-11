package com.jbsa.libraryexercise;


public interface BookService {
    Book retrieveBook(String bookReference) throws BookNotFoundException, WrongBookReferenceException;
    String getBookSummary(String bookReference) throws BookNotFoundException, WrongBookReferenceException;
}
