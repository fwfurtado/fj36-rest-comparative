package br.com.caelum.fj36.rest.controllers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookView {
    private String title;
    private String slug;
    private List<AuthorView> authors = new ArrayList<>();

    /**
     * @deprecated Frameworks only
     */
    @Deprecated
    private BookView() {
    }

    public BookView(String title, String slug, AuthorView... authors) {
        this.title = title;
        this.slug = slug;
        this.authors = Arrays.asList(authors);
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public List<AuthorView> getAuthors() {
        return authors;
    }
}
