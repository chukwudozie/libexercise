package com.jbsa.libraryexercise;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jbsa.libraryexercise.service.impl.BookServiceImplV2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jbsa.libraryexercise.Book;
import com.jbsa.libraryexercise.exceptions.BookNotFoundException;
import com.jbsa.libraryexercise.exceptions.WrongBookReferenceException;
import com.jbsa.libraryexercise.repositories.BookRepo;

//import lombok.SneakyThrows;

@ExtendWith(MockitoExtension.class)
class BookServiceImplV2Test {

    private static final String BOOK_REFERENCE_PREFIX = "BOOK-";
    private static final String THE_GRUFFALO_REFERENCE = BOOK_REFERENCE_PREFIX + "GRUFF472";
    private static final String WINNIE_THE_POOH_REFERENCE = BOOK_REFERENCE_PREFIX + "POOH222";

    @Mock
    private BookRepo bookRepository;

    @InjectMocks
    private BookServiceImplV2 serviceUnderTest;


//    @SneakyThrows
    @Test
    void testRetrieveBook() throws WrongBookReferenceException, BookNotFoundException {
        final String bookReference = THE_GRUFFALO_REFERENCE;
        Book book = new Book(THE_GRUFFALO_REFERENCE, "The Gruffalo", "A mouse taking a walk in the woods");
        when(bookRepository.retrieveBook(bookReference)).thenReturn(book);
        when(bookRepository.existsByReference(bookReference)).thenReturn(true);
        Book retrieveBook = serviceUnderTest.retrieveBook(bookReference);
        verify(bookRepository, atLeast(1)).retrieveBook(bookReference);
        assertAll(
                () -> assertThat(retrieveBook).usingRecursiveComparison().isEqualTo(book),
                () -> assertThat(retrieveBook.getTitle()).isEqualTo("The Gruffalo")
        );
    }


    @Test
    void retrieveBookFailsForInvalidBookReference() {
        String invalidBookReference = "INVALID_REFERENCE";
        WrongBookReferenceException exception = assertThrows(
                WrongBookReferenceException.class,
                () -> serviceUnderTest.retrieveBook(invalidBookReference)
        );
        String expectedErrorMessage = exception.getMessage();
        String actualErrorMessage = "Book reference must begin with BOOK-";
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Test
    void retrieveBookFailsForBookNotInStore() {
        String referenceForBookNotInStore = "BOOK-NOT IN STORE";
        when(bookRepository.existsByReference(referenceForBookNotInStore)).thenReturn(false);
        BookNotFoundException exception = assertThrows(
                BookNotFoundException.class,
                () -> serviceUnderTest.retrieveBook(referenceForBookNotInStore)
        );
        String expectedErrorMessage = exception.getMessage();
        String actualErrorMessage = "Book not found";
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }


    @Test
    void getBookSummarySuccessfulForBookInStore() throws WrongBookReferenceException, BookNotFoundException {
        final Book winnieBook = new Book(WINNIE_THE_POOH_REFERENCE, "Winnie The Pooh", "In this first volume we meet all the friends from the Hundred Acre Wood.");
        when(bookRepository.retrieveBook(WINNIE_THE_POOH_REFERENCE)).thenReturn(winnieBook);
        when(bookRepository.existsByReference(WINNIE_THE_POOH_REFERENCE)).thenReturn(true);
        String actualSummary = "[BOOK-POOH222] Winnie The Pooh -  In this first volume we meet all the friends ...";
        final String expectedBookSummary = serviceUnderTest.getBookSummary("BOOK-POOH222");
        assertAll(
                () -> assertNotNull(expectedBookSummary),
                () -> assertEquals(expectedBookSummary, actualSummary)
        );
    }
}