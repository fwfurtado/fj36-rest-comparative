package br.com.caelum.fj36.rest.service;

import br.com.caelum.fj36.rest.controllers.form.BookForm;
import br.com.caelum.fj36.rest.controllers.view.BookView;
import br.com.caelum.fj36.rest.converter.BookConverter;
import br.com.caelum.fj36.rest.exceptions.BookNotFoundException;
import br.com.caelum.fj36.rest.factory.SlugFactory;
import br.com.caelum.fj36.rest.models.Book;
import br.com.caelum.fj36.rest.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository repository;
    private final BookConverter converter;
    private final SlugFactory slug;

    public BookService(BookRepository repository, BookConverter converter, SlugFactory slug) {
        this.repository = repository;
        this.converter = converter;
        this.slug = slug;
    }

    public List<BookView> findAllOrFilteringByTitle(String title) {
        if (title.isEmpty()) {
            return repository.findAll().stream().map(converter::mappingToViewBy).collect(Collectors.toList());
        }

        return repository.findByTitleLike(title).stream().map(converter::mappingToViewBy).collect(Collectors.toList());
    }

    public Optional<BookView> findBySlug(String slug) {
        Optional<Book> optionalBook = repository.findBySlug(slug);

        return optionalBook.map(converter::mappingToViewBy);
    }

    public void deleteById(Long id) {
        repository.findById(id).orElseThrow(() -> new BookNotFoundException(String.format("Cannot find book with id '%s'", id)));

        repository.deleteById(id);
    }

    public String createBy(BookForm bookForm) {

        Book book = persistBy(bookForm);

        return book.getSlug();
    }

    public BookView updateBookBy(BookForm form) {
        Long id = form.getId();

        repository.findById(id).orElseThrow(() -> new BookNotFoundException(String.format("Cannot find book with id '%s'", id)));

        Book book = persistBy(form);

        return converter.mappingToViewBy(book);
    }

    private Book persistBy(BookForm form) {
        Book book = converter.parseFrom(form);

        slug.generateBy(book::getTitle).andApplyVia(book::setSlug);

        repository.save(book);
        return book;
    }
}
