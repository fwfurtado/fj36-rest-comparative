package br.com.caelum.fj36.rest.configuration;

import br.com.caelum.fj36.rest.controllers.BookResource;
import javax.annotation.PostConstruct;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfiguration extends ResourceConfig {

    @PostConstruct
    public void load() {
        register(BookResource.class);
    }
}
