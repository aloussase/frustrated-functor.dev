package io.github.aloussase.frustratedfunctordotdev.api.posts.controller;

import io.github.aloussase.frustratedfunctordotdev.api.posts.domain.Post;
import io.github.aloussase.frustratedfunctordotdev.api.posts.dtos.NewPostDto;
import io.github.aloussase.frustratedfunctordotdev.api.posts.dtos.PostDto;
import io.github.aloussase.frustratedfunctordotdev.api.posts.dtos.PostPreviewDto;
import io.github.aloussase.frustratedfunctordotdev.api.posts.repository.PostsRepository;
import io.github.aloussase.frustratedfunctordotdev.api.posts.usecase.CreateOrUpdatePostUseCase;
import io.github.aloussase.frustratedfunctordotdev.api.posts.usecase.GetPostByIdUseCase;
import io.github.aloussase.frustratedfunctordotdev.api.posts.usecase.PreviewPostsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostsController {

    private final PostsRepository posts;

    public PostsController(PostsRepository posts) {
        this.posts = posts;
    }

    @GetMapping
    public ResponseEntity<List<PostPreviewDto>> previewPosts() {
        final var useCase = new PreviewPostsUseCase(posts);
        final var previews = useCase.execute();
        final var dtos = previews.stream().map(PostPreviewDto::from).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId) {
        final var useCase = new GetPostByIdUseCase(postId, posts);
        final var post = useCase.execute();
        return post
                .map(PostDto::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Post> createOrUpdatePost(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestBody NewPostDto post
    ) {
        final var useCase = new CreateOrUpdatePostUseCase(posts, id, post.title(), post.summary(), post.content());
        final var newPost = useCase.execute();
        return id == null ?
                ResponseEntity.created(URI.create("/api/v1/posts/" + newPost.id())).body(newPost) :
                ResponseEntity.ok(newPost);
    }

}
