package io.github.aloussase.frustratedfunctordotdev.books;

import java.time.Instant;
import java.util.List;

public record Book(
        int id,
        String author,
        String title,
        String status,
        List<String> tags,
        Instant createdAt,
        Instant lastUpdated
) {
}
