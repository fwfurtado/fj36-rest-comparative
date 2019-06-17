package br.com.caelum.fj36.rest.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Book {

    private Long id;

    private String slug;

    private String title;

    private List<Author> authors = new ArrayList<>();

    /**
     * @deprecated frameworks only or frameworks eyes
     */
    @Deprecated
    private Book() { }

    public Book(String slug, String title, List<Author> authors) {
        this.slug = slug;
        this.title = title;
        this.authors = authors;
    }

    public Book(Long id, String slug, String title, Author... authors) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.authors = Arrays.asList(authors);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public String getSlug() {
        return slug;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Book.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("slug='" + slug + "'")
            .add("title='" + title + "'")
            .add("authors=" + authors)
            .toString();
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
