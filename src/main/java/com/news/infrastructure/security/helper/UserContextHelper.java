package com.news.infrastructure.security.helper;

import com.news.infrastructure.dataprovider.repository.UserRepository;
import com.news.infrastructure.exception.NewsAggregatorException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserContextHelper {

    private final UserRepository userRepository;

    public Long getUserIdFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new NewsAggregatorException(HttpStatus.BAD_REQUEST, "User not authenticated");
        }
        var userDetails = (UserDetails) authentication.getPrincipal();
        var username = userDetails.getUsername();
        var user = userRepository.findByEmail(username).orElseThrow(() -> new NewsAggregatorException(HttpStatus.NOT_FOUND, "User not found"));
        return user.getId();
    }
}
