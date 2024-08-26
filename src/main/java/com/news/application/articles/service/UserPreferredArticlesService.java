package com.news.application.articles.service;

import com.news.application.articles.contract.ArticleResponseDto;
import com.news.infrastructure.client.config.NewsClientConfigurationProperties;
import com.news.infrastructure.dataprovider.model.Preference;
import com.news.infrastructure.dataprovider.repository.PreferenceRepository;
import com.news.infrastructure.security.helper.UserContextHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPreferredArticlesService {

    private final WebClient webClient;

    private final PreferenceRepository preferenceRepository;

    private final NewsClientConfigurationProperties properties;

    private final UserContextHelper userContextHelper;

    public ArticleResponseDto getPreferredArticles(String pageSize, String page) {
        var preferenceEntities = preferenceRepository.findByUserId(userContextHelper.getUserIdFromContext());
        var topics = preferenceEntities.stream().map(Preference::getName).toList();
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/everything/")
                        .queryParam("q", queryBuilder(topics))
                        .queryParam("pageSize", pageSize)
                        .queryParam("page", page)
                        .queryParam("apiKey", properties.getApiKey())
                        .build())
                .retrieve()
                .bodyToMono(ArticleResponseDto.class)
                .block();
    }

    private String queryBuilder(List<String> topics) {
        return topics.stream().reduce((topic1, topic2) -> topic1 + " OR " + topic2).orElse("");
    }
}
