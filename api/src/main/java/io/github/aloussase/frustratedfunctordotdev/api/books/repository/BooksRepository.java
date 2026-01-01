package io.github.aloussase.frustratedfunctordotdev.api.books.repository;

import io.github.aloussase.frustratedfunctordotdev.api.books.domain.Book;

import java.util.List;

public interface BooksRepository {

    /**
     * Create a new book.
     *
     * @param author
     * @param title
     * @param status
     * @param tags
     * @return
     */
    Book createBook(
            String author,
            String title,
            String status,
            List<String> tags
    );

    /**
     * Update the status of a given book.
     *
     * @param bookId    The ID of the book for which to update the status.
     * @param newStatus The new status.
     */
    void updateBook(
            Integer bookId,
            String newStatus,
            List<String> newTags
    );

    List<Book> listBooks();

    List<String> listTags();

}
