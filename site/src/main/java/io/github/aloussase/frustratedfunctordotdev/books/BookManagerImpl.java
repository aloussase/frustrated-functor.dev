package io.github.aloussase.frustratedfunctordotdev.books;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class BookManagerImpl implements BookManager {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${books.api.base_url}")
    private String baseUrl;

    @Value("${books.api.client_id}")
    private String clientId;

    @Value("${books.api.client_secret}")
    private String clientSecret;

    public BookManagerImpl() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public List<Book> listBooks() {
        final var req = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(baseUrl + "/api/v1/books"))
                .header("CF-Access-Client-Id", clientId)
                .header("CF-Access-Client-Secret", clientSecret)
                .build();
        try {
            final var res = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(res.body(), new TypeReference<List<Book>>() {
            });
        } catch (Exception ignored) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<String> listTags() {
        final var req = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(baseUrl + "/api/v1/books/tags"))
                .header("CF-Access-Client-Id", clientId)
                .header("CF-Access-Client-Secret", clientSecret)
                .build();
        try {
            final var res = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(res.body(), new TypeReference<List<String>>() {
            });
        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }
}
