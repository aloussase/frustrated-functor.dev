package io.github.aloussase.frustratedfunctordotdev.bff.books.controller;

import io.github.aloussase.frustratedfunctordotdev.bff.books.dto.BookDto;
import io.github.aloussase.frustratedfunctordotdev.bff.books.dto.NewBookDto;
import io.github.aloussase.frustratedfunctordotdev.bff.books.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> addBook(@RequestBody NewBookDto newBookDto) {
        booksService.addBook(
                newBookDto.author(),
                newBookDto.title(),
                newBookDto.status(),
                newBookDto.tags()
        );
        return ResponseEntity.noContent().build();
    }

    // TODO: Update books.

}
