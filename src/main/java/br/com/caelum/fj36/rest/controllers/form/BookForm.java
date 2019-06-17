package br.com.caelum.fj36.rest.controllers.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookForm {

    @JsonIgnore
    private Long id;

    @NotBlank
    @JsonProperty("title")
    private String title;

    @NotEmpty
    @Size(min = 1)
    @JsonProperty("authors")
    private List<AuthorForm> authors = new ArrayList<>();

    /**
     * @deprecated Frameworks only
     */
    @Deprecated
    private BookForm() {
    }

    public BookForm(String title, AuthorForm... authors) {
        this.title = title;
        this.authors = Arrays.asList(authors);
    }

    public String getTitle() {
        return title;
    }

    public List<AuthorForm> getAuthors() {
        return authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
