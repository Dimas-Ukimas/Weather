package com.dimasukimas.config.util;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

@Configuration
public class EnvConfig {

    @PostConstruct
    public void addDotenvToEnvironment() {
        Dotenv dotenv = Dotenv.load();
        StandardEnvironment environment = new StandardEnvironment();

        PropertySource<?> dotenvPropertySource = new PropertySource<String>("dotenvProperties") {
            @Override
            public Object getProperty(String name) {
                return dotenv.get(name);
            }
        };

        environment.getPropertySources().addFirst(dotenvPropertySource);
    }

}
