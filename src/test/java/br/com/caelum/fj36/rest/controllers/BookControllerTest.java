package br.com.caelum.fj36.rest.controllers;

import static org.junit.Assert.*;

import br.com.caelum.fj36.rest.controllers.form.AuthorForm;
import br.com.caelum.fj36.rest.controllers.form.BookForm;
import br.com.caelum.fj36.rest.controllers.view.BookView;
import br.com.caelum.fj36.rest.models.Book;
import java.net.URI;
import java.util.Arrays;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class BookControllerTest {

    private RestTemplate rest = new RestTemplate();

    @Test
    public void shouldReturnABookBySlug() {

        ResponseEntity<BookView> entity = rest
            .getForEntity("http://localhost:8080/spring/books/{id}", BookView.class, "spring-boot");


        BookView body = entity.getBody();

        System.out.println(entity.getStatusCode());
        System.out.println(entity.getHeaders());
        System.out.println(body);

        assertEquals(HttpStatus.OK, entity.getStatusCode());

    }

    @Test
    public void shouldReturnAllBook() {
        ResponseEntity<BookView[]> entity = rest.getForEntity("http://localhost:8080/spring/books", BookView[].class);

        BookView[] body = entity.getBody();

        System.out.println(entity.getStatusCode());
        System.out.println(entity.getHeaders());
        System.out.println(Arrays.toString(body));

        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void shouldFilterBooksByTitle() {
        ResponseEntity<BookView[]> entity = rest.getForEntity("http://localhost:8080/spring/books?title=s", BookView[].class);

        BookView[] body = entity.getBody();

        System.out.println(entity.getStatusCode());
        System.out.println(entity.getHeaders());
        System.out.println(Arrays.toString(body));

        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void shouldCreateANewBook() {
        AuthorForm fernando = new AuthorForm(1L);

        BookForm form = new BookForm("Java EE", fernando);

        ResponseEntity<Void> entity = rest.postForEntity("http://localhost:8080/spring/books", form, Void.class);

        System.out.println(entity.getStatusCode());
        System.out.println(entity.getHeaders());

        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
        assertEquals(URI.create("/spring/books/java-ee"), entity.getHeaders().getLocation());
    }

    @Test
    public void shouldUpdateANewBook() {
        AuthorForm fernando = new AuthorForm(1L);
        BookForm form = new BookForm("Java EE", fernando);
        ResponseEntity<Void> entity = rest.postForEntity("http://localhost:8080/spring/books", form, Void.class);
        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
        assertEquals(URI.create("/spring/books/java-ee"), entity.getHeaders().getLocation());

        AuthorForm alberto = new AuthorForm(2L);
        BookForm updatedForm = new BookForm("Java EE", fernando, alberto);

        HttpEntity<BookForm> httpEntity = new HttpEntity<>(updatedForm);

        ResponseEntity<BookView> putEntity = rest.exchange("http://localhost:8080/spring/books/4", HttpMethod.PUT, httpEntity, BookView.class);

        assertEquals(HttpStatus.ACCEPTED, putEntity.getStatusCode());
    }
}