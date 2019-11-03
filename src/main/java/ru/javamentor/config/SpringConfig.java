package ru.javamentor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.javamentor.model.User;

@Configuration
public class SpringConfig {
    @Bean
    public User getUser() {
        return new User("anon", 12);
    }
}
