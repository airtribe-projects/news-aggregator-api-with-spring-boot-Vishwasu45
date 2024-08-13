package com.news.usermanagement.service.impl;

import com.news.usermanagement.model.User;
import com.news.usermanagement.repository.UserRepository;
import com.news.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void registerUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setId(UUID.randomUUID());
        userRepository.save(user);
    }

    @Override
    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            //Generate JWT token
            return user;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
