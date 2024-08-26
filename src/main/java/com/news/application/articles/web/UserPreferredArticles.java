package com.news.application.articles.web;

import com.news.application.articles.contract.ArticleResponseDto;
import com.news.application.articles.service.UserPreferredArticlesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class UserPreferredArticles {

    private final UserPreferredArticlesService userPreferredArticlesService;

    @GetMapping
    public ResponseEntity<ArticleResponseDto> getPreferredArticles(@RequestParam("pageSize") String pageSize,
                                                                   @RequestParam("page") String page) {
        var articles = userPreferredArticlesService.getPreferredArticles(pageSize, page);
        return ResponseEntity.ok(articles);
    }
}
