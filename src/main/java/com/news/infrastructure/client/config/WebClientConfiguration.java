package com.news.infrastructure.client.config;

import com.news.infrastructure.client.filter.NewsClientFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfiguration {

    private final NewsClientConfigurationProperties properties;

    @Bean
    public WebClient webClient() {
        WebClient.Builder builder = WebClient.builder();
        builder.baseUrl(properties.getLocation());
        builder.filter(new NewsClientFilter());
        return builder.build();
    }
}
