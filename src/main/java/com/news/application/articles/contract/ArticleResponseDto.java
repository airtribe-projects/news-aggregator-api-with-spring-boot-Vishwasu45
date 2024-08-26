package com.news.application.articles.contract;

import lombok.Data;

import java.util.List;

@Data
public class ArticleResponseDto {

    private String status;

    private int totalResults;

    private List<ArticleDto> articles;
}
