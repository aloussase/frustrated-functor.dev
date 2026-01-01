package io.github.aloussase.frustratedfunctordotdev.api.books.usecase;

import io.github.aloussase.frustratedfunctordotdev.api.books.domain.Book;
import io.github.aloussase.frustratedfunctordotdev.api.books.repository.BooksRepository;
import io.github.aloussase.frustratedfunctordotdev.api.core.usecase.UseCase;

import java.util.List;

public record CreateBookUseCase(
        String author,
        String title,
        String status,
        List<String> tags,
        BooksRepository books
) implements UseCase<Book> {

    @Override
    public Book execute() {
        return books.createBook(author, title, status, tags);
    }
}
