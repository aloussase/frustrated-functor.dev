package io.github.aloussase.frustratedfunctordotdev.bff.books.repository;

import feign.RequestInterceptor;
import io.github.aloussase.frustratedfunctordotdev.bff.books.domain.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Repository
public class RESTBooksRepository implements BooksRepository {
    public RESTBooksRepository(ApiClient api) {
        this.api = api;
    }

    @FeignClient(
            name = "apiClient",
            url = "${books.api.base_url}",
            configuration = ApiClientConfig.class
    )
    public interface ApiClient {
        @GetMapping("/api/v1/books")
        List<Book> getBooks();
    }

    @Configuration
    static class ApiClientConfig {
        @Value("${books.api.client_id}")
        private String clientId;

        @Value("${books.api.client_secret}")
        private String clientSecret;

        @Bean
        public RequestInterceptor providesRequestInterceptor() {
            return template -> {
                template.header("CF-Access-Client-Id", clientId);
                template.header("CF-Access-Client-Secret", clientSecret);
            };
        }
    }

    private final ApiClient api;


    @Override
    public List<Book> findAllBooks() {
        return api.getBooks();
    }
}
