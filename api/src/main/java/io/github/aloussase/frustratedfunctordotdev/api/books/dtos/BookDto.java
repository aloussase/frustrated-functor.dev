package io.github.aloussase.frustratedfunctordotdev.api.books.dtos;

import io.github.aloussase.frustratedfunctordotdev.api.books.domain.Book;

import java.time.Instant;
import java.util.List;

public record BookDto(
        int id,
        String author,
        String title,
        String status,
        List<String> tags,
        Instant createdAt,
        Instant lastUpdated
) {

    public static BookDto from(Book book) {
        return new BookDto(
                book.id(),
                book.author(),
                book.title(),
                book.status(),
                book.tags(),
                book.createdAt(),
                book.lastUpdated());
    }

}
