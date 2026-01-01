package io.github.aloussase.frustratedfunctordotdev;

import java.util.List;

public record WorkEntry(
        String company,
        String date,
        String position,
        String description
) {

    public static List<WorkEntry> entries() {
        return List.of(
                new WorkEntry(
                        "Thoughtworks",
                        "December 2024 - Present",
                        "Consultant",
                        "Worked on the migration of the shopping module of a loyalty platform from a webview to a native mobile experience with SpringBoot and ReactNative."
                ),
                new WorkEntry(
                        "Tia S.A.",
                        "June 2024 - December 2024",
                        "Software Developer",
                        "Created a transactional API with SpringBoot for one of the company's partners to be able to give credit to their employees." +
                                "Also dockerized some ETL scripts in Python and designed database schemas for a new internal application that never came into existence."
                ),
                new WorkEntry(
                        "Escuela Superior Politécnica del Litoral",
                        "June 2024 - December 2024",
                        "Software Developer",
                        "Created an application to monitor students' mental health using .NET and SvelteKit. Here I also managed and automated deployments to our server and " +
                                "designed the application's database schema."
                ),
                new WorkEntry(
                        "Industrial Pesquera Santa Priscila",
                        "Februray 2024 - May 2024",
                        "Internship",
                        "Built web forms for the quality control module of the company's ERP using Laravel and Vue.js"
                )
        );
    }

}
