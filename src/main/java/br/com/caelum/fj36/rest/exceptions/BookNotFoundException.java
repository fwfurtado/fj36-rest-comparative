package br.com.caelum.fj36.rest.exceptions;

public class BookNotFoundException extends IllegalArgumentException {

    public BookNotFoundException(String message) {
        super(message);
    }
}
