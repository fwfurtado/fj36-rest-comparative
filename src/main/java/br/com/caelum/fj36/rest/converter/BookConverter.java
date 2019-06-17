package br.com.caelum.fj36.rest.converter;

import br.com.caelum.fj36.rest.controllers.form.BookForm;
import br.com.caelum.fj36.rest.controllers.view.BookView;
import br.com.caelum.fj36.rest.models.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {

    private final ModelMapper mapper;

    public BookConverter(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Book parseFrom(BookForm form) {
        return mapper.map(form, Book.class);
    }

    public BookView mappingToViewBy(Book book) {
        return mapper.map(book, BookView.class);
    }
}
