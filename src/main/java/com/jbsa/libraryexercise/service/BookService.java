package com.jbsa.libraryexercise.service;


import com.jbsa.libraryexercise.Book;
import com.jbsa.libraryexercise.exceptions.BookNotFoundException;
import com.jbsa.libraryexercise.exceptions.WrongBookReferenceException;

public interface BookService {
    Book retrieveBook(String bookReference) throws BookNotFoundException, WrongBookReferenceException;
    String getBookSummary(String bookReference) throws BookNotFoundException, WrongBookReferenceException;
}
