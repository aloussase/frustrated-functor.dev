package io.github.aloussase.frustratedfunctordotdev.api.posts.dtos;

import io.github.aloussase.frustratedfunctordotdev.api.posts.domain.PostPreview;

public record PostPreviewDto(
        Integer id,
        String title,
        String summary,
        String date
) {
    public static PostPreviewDto from(PostPreview post) {
        return new PostPreviewDto(
                post.postId(),
                post.title(),
                post.summary(),
                post.date()
        );
    }
}
