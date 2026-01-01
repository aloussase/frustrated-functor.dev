package io.github.aloussase.frustratedfunctordotdev.api.posts.repository;

import io.github.aloussase.frustratedfunctordotdev.api.posts.domain.Post;
import io.github.aloussase.frustratedfunctordotdev.api.posts.domain.PostPreview;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class PgPostsRepository implements PostsRepository {
    private final JdbcTemplate template;

    public PgPostsRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<PostPreview> preview() {
        return template.query(
                "select id, title, summary, date from posts",
                new PostPreviewMapper()
        );
    }

    @Override
    public Optional<Post> getById(Integer postId) {
        try {
            final var post = template.queryForObject(
                    "select id, title, date, content, summary from posts where id = ?",
                    new PostRowMapper(),
                    postId
            );
            return Optional.ofNullable(post);
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }

    @Override
    public Post createOrUpdate(Integer postId, String title, String summary, String content) {
        if (postId == null) {
            return template.queryForObject(
                    "insert into posts (title, summary, content) " +
                            "values (?, ?, ?) " +
                            "returning id, title, date, content, summary",
                    new PostRowMapper(), title, summary, content
            );
        } else {
            return template.queryForObject(
                    "insert into posts (id, title, summary, content) " +
                            "values (?, ?, ?, ?) " +
                            "on conflict (id) " +
                            "do update set " +
                            "title = EXCLUDED.title, " +
                            "summary = EXCLUDED.summary, " +
                            "content = EXCLUDED.content " +
                            " returning id, title, date, content, summary",
                    new PostRowMapper(), postId, title, summary, content
            );
        }
    }

    static class PostPreviewMapper implements RowMapper<PostPreview> {
        @Override
        public PostPreview mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new PostPreview(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("summary"),
                    rs.getString("date")
            );
        }
    }

    static class PostRowMapper implements RowMapper<Post> {
        @Override
        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Post(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("date"),
                    rs.getString("content"),
                    rs.getString("summary")
            );
        }
    }
}
