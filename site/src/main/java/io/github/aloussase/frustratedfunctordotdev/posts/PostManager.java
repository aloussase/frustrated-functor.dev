package io.github.aloussase.frustratedfunctordotdev.posts;

import java.util.List;

public interface PostManager {
    List<Post> getPosts();

    Post getPost(String postId);
}
