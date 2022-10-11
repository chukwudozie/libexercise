package com.jbsa.libraryexercise.controller;

import com.jbsa.libraryexercise.Book;
import com.jbsa.libraryexercise.BookRequest;
import com.jbsa.libraryexercise.exceptions.BookNotFoundException;
import com.jbsa.libraryexercise.exceptions.WrongBookReferenceException;
import com.jbsa.libraryexercise.service.BookService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(@Qualifier("stub") BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/retrieve")
    public ResponseEntity<Book> retrieveBookByReferenceNumber(@RequestBody BookRequest request) {
        try {
            return ResponseEntity.ok(bookService.retrieveBook(request.getReference()));
        } catch (BookNotFoundException | WrongBookReferenceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-summary")
    public ResponseEntity<String> getBookSummary(@RequestBody BookRequest request) {
        try {
            return ResponseEntity.ok(bookService.getBookSummary(request.getReference()));
        } catch (BookNotFoundException |WrongBookReferenceException e) {
            throw new RuntimeException(e);
        }
    }

}
