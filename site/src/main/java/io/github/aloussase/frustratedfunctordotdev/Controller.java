package io.github.aloussase.frustratedfunctordotdev;

import io.github.aloussase.frustratedfunctordotdev.books.BookManager;
import io.github.aloussase.frustratedfunctordotdev.posts.PostManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
public class Controller {

    private final PostManager postManager;
    private final BookManager bookManager;

    public Controller(PostManager postManager, BookManager bookManager) {
        this.postManager = postManager;
        this.bookManager = bookManager;
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @PostMapping("/books/filter")
    public String filterBooks(
            Model model,
            @RequestParam("tag") String tag) {
        var books = bookManager.listBooks();

        if (tag != null && !tag.isEmpty()) {
            books = books.stream().filter(book -> book.tags().contains(tag)).toList();
        }

        if (tag != null && tag.isEmpty()) {
            return "redirect:/books";
        }

        model.addAttribute("books", books);

        final var tags = bookManager.listTags();
        tags.addFirst("");
        tags.sort(
                (t1, t2) -> t1.equals(tag) ? -1 : t2.equals(tag) ? 1 : t1.compareTo(t2)
        );
        model.addAttribute("tags", tags);

        return "books";
    }

    @GetMapping("/books")
    public String books(Model model) {
        model.addAttribute("books", bookManager.listBooks());

        final var tags = bookManager.listTags();
        tags.addFirst("");
        model.addAttribute("tags", tags);

        return "books";
    }

    @GetMapping("/cv")
    public String cv(Model model) {
        model.addAttribute("workEntries", WorkEntry.entries());
        model.addAttribute("educationEntries", EducationEntry.entries());
        return "cv";
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        final var posts = postManager.getPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/posts/{postId}")
    public String post(Model model, @PathVariable("postId") String postId) {
        final var post = postManager.getPost(postId);
        model.addAttribute("post", post);
        return "post";
    }

}
