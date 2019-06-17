package br.com.caelum.fj36.rest.controllers.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AuthorForm {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotBlank
    @JsonProperty("name")
    private String name;

    /**
     * @deprecated Frameworks only
     */
    @Deprecated
    private AuthorForm() {
    }

    public AuthorForm(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
