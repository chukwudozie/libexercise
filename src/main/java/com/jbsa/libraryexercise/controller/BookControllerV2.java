package com.jbsa.libraryexercise.controller;

import com.jbsa.libraryexercise.Book;
import com.jbsa.libraryexercise.BookRequest;
import com.jbsa.libraryexercise.exceptions.BookNotFoundException;
import com.jbsa.libraryexercise.exceptions.WrongBookReferenceException;
import com.jbsa.libraryexercise.service.BookService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book/v2")
public class BookControllerV2 {

    private final BookService bookService;

    public BookControllerV2(@Qualifier("jpa") BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/retrieve")
    public ResponseEntity<Book> retrieveBookByReferenceNumber(@RequestBody BookRequest reference) {
        try {
            return ResponseEntity.ok(bookService.retrieveBook(reference.getReference()));
        } catch (BookNotFoundException | WrongBookReferenceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-summary")
    public ResponseEntity<String> getBookSummary(@RequestBody BookRequest bookReference) {
        try {
            return ResponseEntity.ok(bookService.getBookSummary(bookReference.getReference()));
        } catch (BookNotFoundException |WrongBookReferenceException e) {
            throw new RuntimeException(e);
        }
    }
}
