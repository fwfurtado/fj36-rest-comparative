package br.com.caelum.fj36.rest.controllers.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AuthorForm {

    @NotNull
    @JsonProperty("id")
    private Long id;

    /**
     * @deprecated Frameworks only
     */
    @Deprecated
    private AuthorForm() {
    }

    public AuthorForm(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
