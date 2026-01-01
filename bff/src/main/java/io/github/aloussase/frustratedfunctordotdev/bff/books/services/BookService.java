package io.github.aloussase.frustratedfunctordotdev.bff.books.services;

import io.github.aloussase.frustratedfunctordotdev.bff.books.domain.Book;
import io.github.aloussase.frustratedfunctordotdev.bff.books.repository.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BooksRepository books;

    public BookService(BooksRepository books) {
        this.books = books;
    }

    public List<Book> listBooks() {
        return books.findAllBooks();
    }

    public void addBook(String author, String title, String status, List<String> tags) {
        books.addBook(author, title, status, tags);
    }
}
