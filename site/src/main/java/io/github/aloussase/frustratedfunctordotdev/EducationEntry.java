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
                        "Chez moi",
                        "4ever",
                        "Dark Master of Electronic Arts",
                        "I am always reading something or watching someone else read something. Life's too short not to fill every waking and sleeping hour with knowledge."
                ),
                new EducationEntry(
                        "Escuela Superior Politécnica del Litoral",
                        "2019 - Abandoned",
                        "Bsc. Computer Science",
                        "Started with Biology, switched to CS on my 3rd semester. Can't say I learned anything here honestly. I'm sure everyone had good intentions, but intentions never really count towards anything."
                )
        );
    }
}
