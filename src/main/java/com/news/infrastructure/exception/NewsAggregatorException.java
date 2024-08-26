package com.news.infrastructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class NewsAggregatorException extends RuntimeException {

    private HttpStatusCode status;
    private String message;

    public NewsAggregatorException(HttpStatusCode status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public NewsAggregatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
