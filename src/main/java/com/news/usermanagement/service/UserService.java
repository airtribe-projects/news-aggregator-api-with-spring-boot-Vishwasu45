package com.news.usermanagement.service;

import com.news.usermanagement.model.User;

public interface UserService {

    void registerUser(User user);

    User loginUser(String username, String password);
}
