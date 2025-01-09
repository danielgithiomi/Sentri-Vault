package com.githiomi.sentrivault.config;

import com.githiomi.sentrivault.data.model.Blog;
import com.githiomi.sentrivault.data.model.BlogRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Value("${sentri.application.schema}")
    public String schema;

    @Bean
    @ConditionalOnProperty(prefix = "sentri", value = "application.manual-db-populate", havingValue = "true")
    CommandLineRunner commandLineRunner(BlogRepository blogRepository) {

        return args -> {
            System.out.format("Schema name: %s", schema);

            blogRepository.save(
                    new Blog().builder()
                            .title("First Blog")
                            .content("First Blog content")
                            .build()
            );

        };
    }

}
