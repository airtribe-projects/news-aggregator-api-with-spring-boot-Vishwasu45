package com.news.infrastructure.security.helper;

import com.news.infrastructure.dataprovider.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
        assert authentication != null;
        var userDetails = (UserDetails) authentication.getPrincipal();
        var username = userDetails.getUsername();
        var user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getId();
    }
}
