package com.jbsa.libraryexercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BookServiceImpl implements BookService {


    private  final BookRepository1 bookRepository;

    public BookServiceImpl (BookRepository1 bookRepository ) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book retrieveBook(String bookReference) throws BookNotFoundException, WrongBookReferenceException {
        validateBookReference(bookReference);
        return bookRepository.retrieveBook(bookReference);

    }

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
