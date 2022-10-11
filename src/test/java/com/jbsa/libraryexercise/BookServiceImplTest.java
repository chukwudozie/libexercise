package com.jbsa.libraryexercise;

import com.jbsa.libraryexercise.exceptions.BookNotFoundException;
import com.jbsa.libraryexercise.exceptions.WrongBookReferenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookServiceImplTest {


    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookServiceImpl(new BookRepositoryStub());
    }

    @Test
    void retrieveBookSucceedsForBookInStore() throws WrongBookReferenceException, BookNotFoundException {
        assertNotNull(bookService.retrieveBook("BOOK-GRUFF472"));
        String actualBookTitle  = "Winnie The Pooh";
        String expectedBookTitle = bookService.retrieveBook("BOOK-POOH222").getTitle();
        assertEquals(expectedBookTitle, actualBookTitle);
    }

    /**
     *
     Wrong book reference exception thrown
     */
    @Test
    void retrieveBookFailsForInvalidBookReference() {
        String invalidBookReference = "INVALID_REFERENCE";
        WrongBookReferenceException exception  =assertThrows(WrongBookReferenceException.class, ()->bookService.retrieveBook(invalidBookReference));
        String expectedErrorMessage = exception.getMessage();
        String actualErrorMessage = "Book reference must begin with BOOK-";
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    void retrieveBookFailsForBookNotInStore() {
        String referenceForBookNotInStore = "BOOK-NOT IN STORE";
        BookNotFoundException exception  = assertThrows(BookNotFoundException.class, ()->bookService.retrieveBook(referenceForBookNotInStore));
        String expectedErrorMessage = exception.getMessage();
        String actualErrorMessage = "Book not found";
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    void getBookSummarySuccessfulForBookInStore() throws WrongBookReferenceException, BookNotFoundException {
        assertNotNull(bookService.getBookSummary("BOOK-GRUFF472"));
        String actualSummary = "[BOOK-GRUFF472] The Gruffalo -  A mouse taking a walk in the woods";
        String expectedSummary = bookService.getBookSummary("BOOK-GRUFF472");
        assertEquals(expectedSummary,actualSummary);
    }
}