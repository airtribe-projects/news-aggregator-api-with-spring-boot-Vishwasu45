package com.news.usermanagement.service;

import com.news.usermanagement.contract.UserDto;

public interface UserService {

    void registerUser(UserDto user);

    String loginUser(String email, String password);
}
