package io.github.aloussase.frustratedfunctordotdev;

import java.util.List;

public record EducationEntry(
        String institution,
        String date,
        String title,
        String description
) {
    public static List<EducationEntry> entries() {
        return List.of(
                new EducationEntry(
                        "Escuela Superior Politécnica del Litoral",
                        "2019 - Abandoned",
                        "Bsc. Computer Science",
                        ""
                )
        );
    }
}
