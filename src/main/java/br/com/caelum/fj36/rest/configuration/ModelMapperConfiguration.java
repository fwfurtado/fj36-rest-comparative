package br.com.caelum.fj36.rest.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper mapper = new ModelMapper();

        org.modelmapper.config.Configuration configuration = mapper.getConfiguration();

        configuration.setFieldAccessLevel(AccessLevel.PRIVATE);
        configuration.setFieldMatchingEnabled(true);

        return mapper;
    }

}
