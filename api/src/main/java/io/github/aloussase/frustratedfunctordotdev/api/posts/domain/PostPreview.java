package io.github.aloussase.frustratedfunctordotdev.api.posts.domain;

public record PostPreview(
        Integer postId,
        String title,
        String summary,
        String date
) {
}
