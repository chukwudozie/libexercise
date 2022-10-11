package com.jbsa.libraryexercise.service.impl;

import com.jbsa.libraryexercise.Book;
import com.jbsa.libraryexercise.repositories.BookRepository;
import com.jbsa.libraryexercise.exceptions.BookNotFoundException;
import com.jbsa.libraryexercise.exceptions.WrongBookReferenceException;
import com.jbsa.libraryexercise.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service(value = "stub")
public class BookServiceImpl implements BookService {


    private  final BookRepository bookRepository;
    public BookServiceImpl (BookRepository bookRepository ) {
        this.bookRepository = bookRepository;
    }

    /**
     *Implementation using the Book Repository stub provided
     */
    @Override
    public Book retrieveBook(String bookReference) throws BookNotFoundException, WrongBookReferenceException {
        validateBookReference(bookReference);
        return bookRepository.retrieveBook(bookReference);
    }

    /**
     *Implementation using the Book Repository stub provided
     */
    @Override
    public String getBookSummary(String bookReference) throws BookNotFoundException, WrongBookReferenceException {
        validateBookReference(bookReference);
        StringBuilder bookSummary = new StringBuilder();
        Book storedBook = bookRepository.retrieveBook(bookReference);
        bookSummary.append("[").append(storedBook.getReference()).append("]").append(" ").append(storedBook.getTitle()).append(" - ");
        String [] reviewWords = storedBook.getReview().split(" ");
        if (reviewWords.length <= 8) {
            bookSummary.append(Arrays.stream(Arrays.copyOfRange(reviewWords, 0, reviewWords.length))
                    .reduce("", (partialString, value) -> partialString + " " + value));
        } else {
            bookSummary.append(Arrays.stream(Arrays.copyOfRange(reviewWords, 0, 9))
                    .reduce("", (partialString, value) -> partialString + " " + value)).append(" ...");
        }
        return bookSummary.toString();
    }


    private void validateBookReference(String bookReference) throws WrongBookReferenceException,
            BookNotFoundException {
        if(!bookReference.startsWith("BOOK-")){
            throw new WrongBookReferenceException("Book reference must begin with BOOK-");
        }

        if (!bookRepository.existsByReference(bookReference)){
            throw new BookNotFoundException("Book not found");
        }
    }

}
