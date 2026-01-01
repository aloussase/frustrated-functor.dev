package io.github.aloussase.frustratedfunctordotdev.api.books.usecase;

import io.github.aloussase.frustratedfunctordotdev.api.books.repository.BooksRepository;
import io.github.aloussase.frustratedfunctordotdev.api.core.usecase.UseCase;

import java.util.List;

public record UpdateBookUseCase(
        Integer bookId,
        String newStatus,
        List<String> newTags,
        BooksRepository books
) implements UseCase<Void> {
    @Override
    public Void execute() {
        books.updateBook(bookId, newStatus, newTags);
        return null;
    }
}
