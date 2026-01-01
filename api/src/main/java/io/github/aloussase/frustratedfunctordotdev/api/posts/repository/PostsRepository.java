package io.github.aloussase.frustratedfunctordotdev.api.posts.repository;

import io.github.aloussase.frustratedfunctordotdev.api.posts.domain.Post;
import io.github.aloussase.frustratedfunctordotdev.api.posts.domain.PostPreview;

import java.util.List;
import java.util.Optional;

public interface PostsRepository {

    /**
     * Get a preview of all posts.
     *
     * @return A list of post previews.
     */
    List<PostPreview> preview();

    /**
     * Get a post by ID.
     *
     * @param postId The ID of the post to retrieve.
     * @return Maybe the post, maybe go fuck yourself.
     */
    Optional<Post> getById(Integer postId);

    /**
     * Create or update a post.
     *
     * @param postId  The ID of the post to update (optional).
     * @param title   The title of the post
     * @param summary The post summary
     * @param content The post content
     * @return The created/updated post
     */
    Post createOrUpdate(Integer postId, String title, String summary, String content);

}
