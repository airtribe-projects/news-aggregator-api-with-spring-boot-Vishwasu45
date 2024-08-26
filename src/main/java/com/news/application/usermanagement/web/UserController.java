package com.news.application.usermanagement.web;

import com.news.application.usermanagement.contract.UserDto;
import com.news.application.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public void registerUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserDto userDto) {
        return userService.loginUser(userDto.getEmail(), userDto.getPassword());
    }
}
