package com.jbsa.libraryexercise;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/retrieve")
    public ResponseEntity<?> retrieveBookByReferenceNumber(@RequestBody String reference) {
        try {
            return ResponseEntity.ok(bookService.retrieveBook(reference));
        } catch (BookNotFoundException | WrongBookReferenceException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-summary")
    public ResponseEntity<?> getBookSummary(@RequestBody String bookReference) {
        try {
            return ResponseEntity.ok(bookService.getBookSummary(bookReference));
        } catch (BookNotFoundException |WrongBookReferenceException e) {
            throw new RuntimeException(e);
        }
    }

}
