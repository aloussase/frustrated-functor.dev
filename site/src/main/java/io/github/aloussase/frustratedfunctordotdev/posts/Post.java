package io.github.aloussase.frustratedfunctordotdev.posts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record Post(
        String id,
        String title,
        String date,
        String content,
        String summary
) implements Comparable<Post> {

    @Override
    public int compareTo(Post o) {
        final var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final var d1 = LocalDate.from(formatter.parse(this.date.split(" ")[0]));
        final var d2 = LocalDate.from(formatter.parse(o.date.split(" ")[0]));
        return d2.compareTo(d1);
    }
}
