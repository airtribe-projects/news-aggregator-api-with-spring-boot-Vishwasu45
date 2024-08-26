package com.news.application.usermanagement.service;

import com.news.application.usermanagement.contract.UserDto;

public interface UserService {

    void registerUser(UserDto userDto);

    String loginUser(UserDto userDto);
}
