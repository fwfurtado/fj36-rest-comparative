package br.com.caelum.fj36.rest.controllers;

import br.com.caelum.fj36.rest.controllers.form.BookForm;
import br.com.caelum.fj36.rest.controllers.view.BookView;
import br.com.caelum.fj36.rest.models.Book;
import br.com.caelum.fj36.rest.repository.BookRepository;
import br.com.caelum.fj36.rest.service.BookService;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component //because we uses spring container
//@Singleton, @Stateless, @Stateful // create a singleton only when using application server with java ee
//@Application, @Request, @Session
@Path("books")
@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
public class BookResource {

    private final BookService service;

    public BookResource(BookService service) {
        this.service = service;
    }

    @GET
    public List<BookView> list(@QueryParam("title") @DefaultValue("") String title) {
        return service.findAllOrFilteringByTitle(title);
    }

    @GET
    @Path("{slug}")
    public Response show(@PathParam("slug") String slug) {
        return service.findBySlug(slug)
                    .map(book -> Response.ok(book).build())
                        .orElse(Response.status(Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        service.deleteById(id);

        return Response.noContent().build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response createBy(@Valid BookForm bookForm) {
        String slug = service.createBy(bookForm);

        URI uri = URI.create("/jax-rs/books/" + slug);

        return Response.created(uri).build();
    }


    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") Long id,@Valid BookForm form) {
        form.setId(id);

        BookView book = service.updateBookBy(form);

        return Response.accepted().entity(book).build();


    }
}
