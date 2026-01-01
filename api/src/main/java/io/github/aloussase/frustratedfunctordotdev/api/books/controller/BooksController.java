package io.github.aloussase.frustratedfunctordotdev.api.books.controller;

import io.github.aloussase.frustratedfunctordotdev.api.books.dtos.BookDto;
import io.github.aloussase.frustratedfunctordotdev.api.books.dtos.NewBookDto;
import io.github.aloussase.frustratedfunctordotdev.api.books.dtos.UpdateBookDto;
import io.github.aloussase.frustratedfunctordotdev.api.books.repository.BooksRepository;
import io.github.aloussase.frustratedfunctordotdev.api.books.usecase.CreateBookUseCase;
import io.github.aloussase.frustratedfunctordotdev.api.books.usecase.ListBooksUseCase;
import io.github.aloussase.frustratedfunctordotdev.api.books.usecase.ListTagsUseCase;
import io.github.aloussase.frustratedfunctordotdev.api.books.usecase.UpdateBookUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BooksController {

    private final BooksRepository books;

    public BooksController(BooksRepository books) {
        this.books = books;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks() {
        final var useCase = new ListBooksUseCase(books);
        final var books = useCase.execute();
        return ResponseEntity.ok(
                books.stream()
                        .map(BookDto::from)
                        .toList()
        );
    }

    @GetMapping("/tags")
    public ResponseEntity<List<String>> getTags() {
        final var useCase = new ListTagsUseCase(books);
        final var tags = useCase.execute();
        return ResponseEntity.ok(tags);
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody NewBookDto newBook) {
        final var useCase = new CreateBookUseCase(
                newBook.author(),
                newBook.title(),
                newBook.status(),
                newBook.tags(),
                books);
        final var book = useCase.execute();
        final var dto = BookDto.from(book);
        return ResponseEntity
                .created(URI.create("/api/v1/books/" + book.id()))
                .body(dto);
    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<Void> updateBook(
            @PathVariable Integer bookId,
            @RequestBody UpdateBookDto book) {
        final var useCase = new UpdateBookUseCase(bookId, book.status(), book.tags(), books);
        useCase.execute();
        return ResponseEntity.noContent().build();
    }


}
