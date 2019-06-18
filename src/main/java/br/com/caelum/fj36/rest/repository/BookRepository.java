package br.com.caelum.fj36.rest.repository;

import static java.util.Objects.isNull;

import br.com.caelum.fj36.rest.models.Author;
import br.com.caelum.fj36.rest.models.Book;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    private static final Map<Long, Book> database = new HashMap<>();
    private static final AtomicLong counter = new AtomicLong();

    static {
        Author fernando = new Author(1L, "Fernando");
        Author alberto = new Author(2L, "Alberto");
        Author harada = new Author(3L, "Harada");

        database.put(counter.incrementAndGet(), new Book(counter.get(), "spring-boot", "Spring Boot", fernando, alberto));
        database.put(counter.incrementAndGet(), new Book(counter.get(), "jax-rs","Jax-RS", fernando));
        database.put(counter.incrementAndGet(), new Book(counter.get(), "soa", "SOA", harada));
    }


    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    public Optional<Book> findBySlug(String slug) {
        return database.values()
                    .stream()
                        .filter(book -> slug.equals(book.getSlug()))
                            .findFirst();
    }

    public void save(Book book) {

        if (isNull(book.getId())) {
            long id = counter.incrementAndGet();

            book.setId(id);
        }

        database.put(book.getId(), book);
    }

    public void deleteById(Long id) {
        database.remove(id);
    }


    public List<Book> findAll() {
        return new ArrayList<>(database.values());
    }

    public List<Book> findByTitleLike(String title) {
        return database.values().stream()
                    .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                        .collect(Collectors.toList());
    }

}
