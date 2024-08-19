package com.news.usermanagement.service.impl;

import com.news.usermanagement.config.JwtHelper;
import com.news.usermanagement.contract.UserDto;
import com.news.usermanagement.mapper.UserMapper;
import com.news.usermanagement.model.User;
import com.news.usermanagement.repository.UserRepository;
import com.news.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;

    private final JwtHelper jwtHelper;

    @Override
    public void registerUser(UserDto userDto) {
        var user = userMapper.mapUserDtoToUser(userDto);
        userRepository.save(user);
    }

    @Override
    public String loginUser(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        return jwtHelper.generateToken(new User(email, password));
    }
}
