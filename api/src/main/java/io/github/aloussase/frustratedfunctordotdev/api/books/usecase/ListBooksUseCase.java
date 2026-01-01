package io.github.aloussase.frustratedfunctordotdev.api.books.usecase;

import io.github.aloussase.frustratedfunctordotdev.api.books.domain.Book;
import io.github.aloussase.frustratedfunctordotdev.api.books.repository.BooksRepository;
import io.github.aloussase.frustratedfunctordotdev.api.core.usecase.UseCase;

import java.util.List;

public record ListBooksUseCase(
        BooksRepository books
) implements UseCase<List<Book>> {
    @Override
    public List<Book> execute() {
        return books.listBooks();
    }
}
