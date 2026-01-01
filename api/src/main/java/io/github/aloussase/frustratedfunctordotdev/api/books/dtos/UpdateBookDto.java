package io.github.aloussase.frustratedfunctordotdev.api.books.dtos;

import java.util.List;

public record UpdateBookDto(
        String status,
        List<String> tags
) {
}
