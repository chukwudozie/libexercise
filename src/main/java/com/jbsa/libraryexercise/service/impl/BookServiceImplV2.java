package com.jbsa.libraryexercise.service.impl;

import com.jbsa.libraryexercise.Book;
import com.jbsa.libraryexercise.repositories.BookRepo;
import com.jbsa.libraryexercise.exceptions.BookNotFoundException;
import com.jbsa.libraryexercise.exceptions.WrongBookReferenceException;
import com.jbsa.libraryexercise.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service(value = "jpa")
public class BookServiceImplV2 implements BookService {

    private final BookRepo bookRepo;

    public BookServiceImplV2(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public Book retrieveBook(String bookReference) throws BookNotFoundException, WrongBookReferenceException {
        validateBookReference(bookReference);
        return bookRepo.retrieveBook(bookReference);
    }

    @Override
    public String getBookSummary(String bookReference) throws BookNotFoundException, WrongBookReferenceException {
        validateBookReference(bookReference);
        StringBuilder bookSummary = new StringBuilder();
        Book storedBook = bookRepo.retrieveBook(bookReference);
        bookSummary.append("[").append(storedBook.getReference()).append("]").append(" ").append(storedBook.getTitle()).append(" - ");
        String [] reviewWords = storedBook.getReview().split(" ");
        return (reviewWords.length <= 8)? bookSummary.append(Arrays.stream(Arrays.copyOfRange(reviewWords, 0, reviewWords.length))
                .reduce("", (partialString, value) -> partialString + " " + value)).toString(): bookSummary.append(Arrays.stream(Arrays.copyOfRange(reviewWords, 0, 9))
                .reduce("", (partialString, value) -> partialString + " " + value)).append(" ...").toString();
          }

    private void validateBookReference(String bookReference) throws WrongBookReferenceException,
            BookNotFoundException {
        if(!bookReference.startsWith("BOOK-")){
            throw new WrongBookReferenceException("Book reference must begin with BOOK-");
        }

        if (!bookRepo.existsByReference(bookReference)){
            throw new BookNotFoundException("Book not found");
        }
    }
}
