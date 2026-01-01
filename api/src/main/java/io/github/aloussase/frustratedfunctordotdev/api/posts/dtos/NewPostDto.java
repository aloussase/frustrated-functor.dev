package io.github.aloussase.frustratedfunctordotdev.api.posts.dtos;

public record NewPostDto(
        String title,
        String content,
        String summary
) {
}
