package io.github.aloussase.frustratedfunctordotdev.api.books.repository;

import io.github.aloussase.frustratedfunctordotdev.api.books.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PgBooksRepository implements BooksRepository {

    private final JdbcTemplate template;

    public PgBooksRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Book createBook(String author, String title, String status, List<String> tags) {
        final var id = template.queryForObject(
                "insert into books (author, title, status) values (?, ?, ?) returning id",
                Integer.class,
                author, title, status);

        setBookTags(id, tags);

        return getBook(id);
    }

    @Override
    public void updateBook(Integer bookId, String newStatus, List<String> newTags) {
        if (newTags != null) {
            setBookTags(bookId, newTags);
        }

        if (newStatus == null)
            return;

        final var sb = new StringBuilder("update books ");

        if (newStatus != null) {
            sb.append(" set status = ? ");
        }

        sb.append(" where id = ? ");

        template.update(sb.toString(), newStatus, bookId);
    }

    private void setBookTags(Integer bookId, List<String> tags) {
        clearBookTags(bookId);

        if (tags == null || tags.isEmpty()) {
            return;
        }

        final var tagsList = "(" +
                tags.stream().map(t -> "'" + t + "'").collect(Collectors.joining(","))
                + ")";

        template.update("""
                insert into books_tags (book_id, tag_id)
                select ?, t.id
                from book_tags t
                where t.name in 
                """ + tagsList, bookId);
    }

    private void clearBookTags(Integer bookId) {
        template.update("delete from books_tags where book_id = ?", bookId);
    }

    private Book getBook(Integer bookId) {
        return template.queryForObject("""
                select b.id, b.author, b.title, b.status, array_agg(t.name) as tags,
                       b.created_at, b.last_updated from
                    books b
                        left join books_tags bt on bt.book_id = b.id
                        left join  book_tags t on t.id = bt.tag_id
                where b.id = ?
                group by b.id
                order by last_updated desc;
                """, new BookRowMapper(), bookId);
    }

    public List<Book> listBooks() {
        return template.query("""
                select b.id, b.author, b.title, b.status, array_agg(t.name) as tags,
                       b.created_at, b.last_updated from
                    books b
                        left join books_tags bt on bt.book_id = b.id
                        left join  book_tags t on t.id = bt.tag_id
                group by b.id
                order by last_updated desc;
                """, new BookRowMapper());
    }

    @Override
    public List<String> listTags() {
        return template.query(
                "select name from book_tags",
                (rs, n) -> rs.getString("name")
        );
    }


    static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            final var id = rs.getInt("id");
            final var title = rs.getString("title");
            final var author = rs.getString("author");
            final var status = rs.getString("status");
            final var tags = (String[]) Optional.ofNullable(rs.getArray("tags"))
                    .map(ary -> {
                        try {
                            return ary.getArray();
                        } catch (SQLException e) {
                            return new String[0];
                        }
                    })
                    .orElseGet(() -> new String[0]);
            final var createdAt = rs.getTimestamp("created_at");
            final var lastUpdated = rs.getTimestamp("last_updated");
            return new Book(
                    id, author, title, status, tags.length == 1 && tags[0] == null ? List.of() : Arrays.asList(tags),
                    createdAt.toInstant(), lastUpdated.toInstant());
        }
    }
}
