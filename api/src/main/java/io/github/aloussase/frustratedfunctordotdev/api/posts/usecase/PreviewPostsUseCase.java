package io.github.aloussase.frustratedfunctordotdev.api.posts.usecase;

import io.github.aloussase.frustratedfunctordotdev.api.core.usecase.UseCase;
import io.github.aloussase.frustratedfunctordotdev.api.posts.domain.PostPreview;
import io.github.aloussase.frustratedfunctordotdev.api.posts.repository.PostsRepository;

import java.util.List;

public record PreviewPostsUseCase(
        PostsRepository posts
) implements UseCase<List<PostPreview>> {
    @Override
    public List<PostPreview> execute() {
        return posts.preview();
    }
}
