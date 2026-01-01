package io.github.aloussase.frustratedfunctordotdev.bff.books.controller;

import io.github.aloussase.frustratedfunctordotdev.bff.books.dto.BookDto;
import io.github.aloussase.frustratedfunctordotdev.bff.books.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BooksController {

    private final BookService booksService;

    public BooksController(BookService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> listBooks() {
        final var books = booksService.listBooks();
        final var dto = books.stream().map(BookDto::from).toList();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<?> addBook() {
        return null;
    }

    // TODO: Update books.

}
