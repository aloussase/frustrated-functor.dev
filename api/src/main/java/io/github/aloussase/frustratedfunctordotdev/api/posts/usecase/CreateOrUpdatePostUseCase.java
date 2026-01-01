package io.github.aloussase.frustratedfunctordotdev.api.posts.usecase;

import io.github.aloussase.frustratedfunctordotdev.api.core.usecase.UseCase;
import io.github.aloussase.frustratedfunctordotdev.api.posts.domain.Post;
import io.github.aloussase.frustratedfunctordotdev.api.posts.repository.PostsRepository;

public record CreateOrUpdatePostUseCase(
        PostsRepository posts,
        Integer postId,
        String title,
        String summary,
        String content
) implements UseCase<Post> {
    @Override
    public Post execute() {
        return posts.createOrUpdate(postId, title, summary, content);
    }
}
