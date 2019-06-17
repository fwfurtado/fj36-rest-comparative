package br.com.caelum.fj36.rest.controllers.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorView {
    @JsonProperty("name")
    private String name;

    /**
     * @deprecated Frameworks only
     */
    @Deprecated
    private AuthorView() {
    }

    public AuthorView(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
