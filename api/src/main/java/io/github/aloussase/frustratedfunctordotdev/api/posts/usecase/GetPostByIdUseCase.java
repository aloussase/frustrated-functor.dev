package io.github.aloussase.frustratedfunctordotdev.api.posts.usecase;

import io.github.aloussase.frustratedfunctordotdev.api.core.usecase.UseCase;
import io.github.aloussase.frustratedfunctordotdev.api.posts.domain.Post;
import io.github.aloussase.frustratedfunctordotdev.api.posts.repository.PostsRepository;

import java.util.Optional;

public record GetPostByIdUseCase(
        Integer postId,
        PostsRepository posts
) implements UseCase<Optional<Post>> {
    @Override
    public Optional<Post> execute() {
        return posts.getById(postId);
    }
}
