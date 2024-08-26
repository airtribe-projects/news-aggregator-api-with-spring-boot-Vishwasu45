package com.news.application.usermanagement.service;

import com.news.application.usermanagement.contract.UserDto;

public interface UserService {

    void registerUser(UserDto user);

    String loginUser(String email, String password);
}
