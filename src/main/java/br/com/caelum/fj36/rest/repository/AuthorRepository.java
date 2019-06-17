package br.com.caelum.fj36.rest.repository;

import br.com.caelum.fj36.rest.models.Author;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepository {

    private static final Map<Long, Author> database = new HashMap<>();
    private static final AtomicLong counter = new AtomicLong();

    static {
        database.put(counter.incrementAndGet(), new Author(counter.get(), "Fernando"));
        database.put(counter.incrementAndGet(), new Author(counter.get(), "Alberto"));
        database.put(counter.incrementAndGet(), new Author(counter.get(), "Harada"));
    }

    public Optional<Author> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    public void save(Author author) {
        author.setId(counter.incrementAndGet());

        database.put(counter.get(), author);
    }
}
