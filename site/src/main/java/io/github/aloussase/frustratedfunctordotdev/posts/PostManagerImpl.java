package io.github.aloussase.frustratedfunctordotdev.posts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;


@Slf4j
@Service
public class PostManagerImpl implements PostManager {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${posts.api.base_url}")
    private String baseUrl;

    @Value("${posts.api.client_id}")
    private String clientId;

    @Value("${posts.api.client_secret}")
    private String clientSecret;

    public PostManagerImpl() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public List<Post> getPosts() {
        final var req = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(baseUrl + "/api/v1/posts"))
                .header("CF-Access-Client-Id", clientId)
                .header("CF-Access-Client-Secret", clientSecret)
                .build();
        try {
            final var res = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
            return objectMapper
                    .readValue(res.body(), new TypeReference<List<Post>>() {
                    })
                    .stream()
                    .sorted()
                    .toList();
        } catch (Exception ex) {
            log.error("Error while listing posts from API", ex);
            return Collections.emptyList();
        }
    }

    @Override
    public Post getPost(String postId) {
        final var req = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(baseUrl + "/api/v1/posts/" + postId))
                .header("CF-Access-Client-Id", clientId)
                .header("CF-Access-Client-Secret", clientSecret)
                .build();
        try {
            final var res = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(res.body(), Post.class);
        } catch (Exception ignored) {
            // TODO Handle exception
            return null;
        }
    }

}
