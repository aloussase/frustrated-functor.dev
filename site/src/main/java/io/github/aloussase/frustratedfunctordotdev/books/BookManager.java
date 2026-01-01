package io.github.aloussase.frustratedfunctordotdev.books;

import java.util.List;

public interface BookManager {

    /**
     * @return A list of all books.
     */
    List<Book> listBooks();

    /**
     * @return A list of available tags.
     */
    List<String> listTags();

}
