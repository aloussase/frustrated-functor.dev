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
                        "Love this place. Been learning a lot and doing lots of fun stuff. Also, had my first service outage and that was ... not so fun, but quite an enriching experience! \uD83D\uDCAF would recommend"
                ),
                new WorkEntry(
                        "Tia S.A.",
                        "June 2024 - December 2024",
                        "Software Developer",
                        "The only interesting thing I did here was building a REST API in SpringBoot for an external partner to give credit points to their employees. Even so, most of the logic was in the database (not my choice) and my service only invoked the stored procedures in it. This took me about 2 weeks and the rest of the time I did literally nothing work-related. And my experience is that national industry is like this: managers like to complain but they don't know how to manage teams nor resources, and almost no one with a tech product actually knows how to produce good tech."
                ),
                new WorkEntry(
                        "Escuela Superior Politécnica del Litoral",
                        "June 2024 - December 2024",
                        "Software Developer",
                        """
                                A mobile app with Flutter and accompanying API with .NET. Also, managed deployments on a provided server and automated them by installing a GitLab runner on it. Was a pretty fun experience since I had complete control over the stack.
                                
                                Fun story: I (along with a colleague) rewrote the entire first version of the web dashboard from Next.js to SvelteKit. Of course, I asked for permission first and was granted it. Then, when the time came to hand over the app, everyone lost their minds because IT SHOULD BE WRITTEN IN REACT!!! WE ARE REACT DEVELOPERS WE CAN'T MAINTAIN A SVELTEKIT APP!!!! JAVASCRIPT? NEVER HEARD OF 'ER."""
                ),
                new WorkEntry(
                        "Industrial Pesquera Santa Priscila",
                        "Februray 2024 - May 2024",
                        "Internship",
                        "Boring forms with Vue and Laravel"
                )
        );
    }

}
