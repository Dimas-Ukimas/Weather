package com.dimasukimas.config.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(@Value("${open-weather.api.url}") String baseUrl
    ) {
        if (baseUrl.isBlank()) {
            throw new IllegalArgumentException("Property 'open-weather.api.url' is absent or blank");
        }

        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
