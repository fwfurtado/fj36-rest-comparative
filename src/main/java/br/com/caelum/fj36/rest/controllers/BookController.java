package br.com.caelum.fj36.rest.controllers;

import br.com.caelum.fj36.rest.controllers.form.BookForm;
import br.com.caelum.fj36.rest.controllers.view.BookView;
import br.com.caelum.fj36.rest.models.Book;
import br.com.caelum.fj36.rest.repository.BookRepository;
import br.com.caelum.fj36.rest.service.BookService;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.xml.ws.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "books", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<BookView> list(@RequestParam(required = false, name = "title", defaultValue = "") String title) {
       return service.findAllOrFilteringByTitle(title);
    }

    @GetMapping("{slug}")
    public ResponseEntity<BookView> show(@PathVariable("slug") String slug) {

        return service.findBySlug(slug)
                    .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        service.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> createBy(@RequestBody @Valid BookForm bookForm) {
        String slug = service.createBy(bookForm);

        URI uri = URI.create("/spring/books/" + slug);

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody BookForm form) {
        form.setId(id);

        BookView book = service.updateBookBy(form);

        return ResponseEntity.accepted().body(book);

    }
}
