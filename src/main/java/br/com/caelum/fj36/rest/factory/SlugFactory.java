package br.com.caelum.fj36.rest.factory;

import br.com.caelum.fj36.rest.models.Book;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SlugFactory {

    public <T> TargetSlugGenerator<T> generateBy(Supplier<String> supplier) {

        String sourceSlug = supplier.get();

        String slug = sourceSlug.toLowerCase().replace(" ", "-");

        return new TargetSlugGenerator<>(slug);
    }

    public class TargetSlugGenerator<T> {

        private final String slug;

        private TargetSlugGenerator(String slug) {
            this.slug = slug;
        }

        public void andApplyVia(Consumer<String> mapping) {
            mapping.accept(slug);
        }
    }
}
