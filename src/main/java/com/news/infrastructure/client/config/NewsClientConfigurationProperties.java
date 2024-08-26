package com.news.infrastructure.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "news.client")
@Data
public class NewsClientConfigurationProperties {

    private String location;

    private String apiKey;
}
