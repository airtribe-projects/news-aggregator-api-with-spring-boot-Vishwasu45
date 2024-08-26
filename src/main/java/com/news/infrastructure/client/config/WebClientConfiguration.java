package com.news.infrastructure.client.config;

import com.news.infrastructure.client.NewsClient;
import com.news.infrastructure.client.filter.NewsClientFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

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

    @Bean
    public NewsClient newsClient(WebClient webClient) {
        return HttpServiceProxyFactory
                .builder()
                .exchangeAdapter(WebClientAdapter.create(webClient))
                .build()
                .createClient(NewsClient.class);
    }
}
