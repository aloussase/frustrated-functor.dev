package io.github.aloussase.frustratedfunctordotdev.api.books.dtos;

import java.util.List;

public record NewBookDto(
       String author,
       String title,
       String status,
       List<String> tags
) {
}
