package com.news.infrastructure.client;

import com.news.application.articles.contract.ArticleResponseDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Map;

public interface NewsClient {

    @GetExchange("/v2/everything")
    ArticleResponseDto getArticles(@RequestParam Map<String, ?> params);
}
