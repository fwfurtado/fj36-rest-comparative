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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response.StatusType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
public class BookResourceTest {
    private Client client;

    @Before
    public void setUp()  {
        client = ClientBuilder.newClient();
    }

    @Test
    public void shouldReturnABookBySlug() {

        Response response = client
            .target("http://localhost:8080/jax-rs/books/spring-boot")
            .request()
            .get();

        Book body = response.readEntity(Book.class);

        System.out.println(response.getStatusInfo());
        System.out.println(response.getStringHeaders());
        System.out.println(body);

        assertEquals(Status.OK, response.getStatusInfo().toEnum());
    }

    @Test
    public void shouldReturnAllBook() {
        Response response = client.target("http://localhost:8080/jax-rs/books")
                            .request()
                                .get();

        BookView[] body = response.readEntity(BookView[].class);

        System.out.println(response.getStatusInfo());
        System.out.println(response.getStringHeaders());
        System.out.println(Arrays.toString(body));

        assertEquals(Status.OK, response.getStatusInfo().toEnum());
    }

    @Test
    public void shouldFilterBooksByTitle() {
        Response response = client.target("http://localhost:8080/jax-rs/books?title=s")
                            .request()
                                .get();

        BookView[] body = response.readEntity(BookView[].class);

        System.out.println(response.getStatusInfo());
        System.out.println(response.getStringHeaders());
        System.out.println(Arrays.toString(body));

        assertEquals(Status.OK, response.getStatusInfo().toEnum());
    }

    @Test
    public void shouldCreateANewBook() {
        AuthorForm fernando = new AuthorForm(1L);

        BookForm form = new BookForm("Java EE", fernando);


        Response response = client.target("http://localhost:8080/jax-rs/books")
                                .request()
                                    .post(Entity.json(form));

        System.out.println(response.getStatusInfo());
        System.out.println(response.getStringHeaders());

        assertEquals(Status.CREATED, response.getStatusInfo().toEnum());
        assertEquals("http://localhost:8080/jax-rs/books/java-ee", response.getHeaderString("Location"));
    }

    @Test
    public void shouldUpdateANewBook() {
        AuthorForm fernando = new AuthorForm(1L);
        BookForm form = new BookForm("Java EE", fernando);

        Response response = client.target("http://localhost:8080/jax-rs/books")
                            .request()
                                .post(Entity.json(form));

        System.out.println(response.getStatusInfo());
        System.out.println(response.getStringHeaders());

        assertEquals(Status.CREATED, response.getStatusInfo().toEnum());
        assertEquals("http://localhost:8080/jax-rs/books/java-ee", response.getHeaderString("Location"));

        AuthorForm alberto = new AuthorForm(2L);
        BookForm updatedForm = new BookForm("Java EE", fernando, alberto);
        HttpEntity<BookForm> httpEntity = new HttpEntity<>(form);

        Response putResponse = client.target("http://localhost:8080/jax-rs/books/4")
            .request()
            .put(Entity.json(form));


        assertEquals(Status.ACCEPTED, putResponse.getStatusInfo());
    }
}