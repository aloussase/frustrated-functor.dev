package io.github.aloussase.frustratedfunctordotdev.bff.books.repository;

import io.github.aloussase.frustratedfunctordotdev.bff.books.domain.Book;

import java.util.List;

public interface BooksRepository {

    List<Book> findAllBooks();

    void addBook(String author, String title, String status, List<String> tags);

}
