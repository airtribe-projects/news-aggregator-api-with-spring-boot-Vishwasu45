package com.news.application.articles.contract;

import lombok.Data;

@Data
public class ArticleDto {

    private String author;

    private String title;

    private String description;

    private String content;
}
