package br.com.caelum.fj36.rest;

import br.com.caelum.fj36.rest.exceptions.BookNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
//@Order(Ordered.LOWEST_PRECEDENCE)
public class SpringExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handle(BookNotFoundException e) {
        System.out.println(e.getMessage());

        return ResponseEntity.notFound().build();
    }
}
