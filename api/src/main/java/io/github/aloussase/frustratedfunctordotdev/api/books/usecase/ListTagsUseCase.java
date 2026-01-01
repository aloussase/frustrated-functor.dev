package io.github.aloussase.frustratedfunctordotdev.api.books.usecase;

import io.github.aloussase.frustratedfunctordotdev.api.books.repository.BooksRepository;
import io.github.aloussase.frustratedfunctordotdev.api.core.usecase.UseCase;

import java.util.List;

public record ListTagsUseCase(
        BooksRepository books
) implements UseCase<List<String>> {
    @Override
    public List<String> execute() {
        return books.listTags();
    }
}
