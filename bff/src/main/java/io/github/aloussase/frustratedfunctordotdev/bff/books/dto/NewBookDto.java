package io.github.aloussase.frustratedfunctordotdev.bff.books.dto;

import java.util.List;

public record NewBookDto(
        String author,
        String title,
        String status,
        List<String> tags
) {
}
