package io.github.aloussase.frustratedfunctordotdev.api.posts.dtos;

import io.github.aloussase.frustratedfunctordotdev.api.posts.domain.Post;

public record PostDto(
        Integer id,
        String title,
        String date,
        String content,
        String summary
) {

    public static PostDto from(Post post) {
        return new PostDto(
                post.id(),
                post.title(),
                post.date(),
                post.content(),
                post.summary()
        );
    }

}
