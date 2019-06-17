package br.com.caelum.fj36.rest.models;

public class Author {

    private Long id;
    private String name;

    /**
     * @deprecated frameworks only
     */
    @Deprecated
    private Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
