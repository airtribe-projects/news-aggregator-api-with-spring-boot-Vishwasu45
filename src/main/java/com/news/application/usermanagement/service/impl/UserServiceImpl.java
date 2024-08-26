package com.news.application.usermanagement.service.impl;

import com.news.infrastructure.dataprovider.repository.UserRepository;
import com.news.infrastructure.exception.NewsAggregatorException;
import com.news.infrastructure.security.helper.JwtHelper;
import com.news.application.usermanagement.contract.UserDto;
import com.news.application.usermanagement.mapper.UserMapper;
import com.news.application.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

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
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new NewsAggregatorException(HttpStatus.BAD_REQUEST, "User already exists");
        }
        userRepository.save(user);
    }

    @Override
    public String loginUser(UserDto userDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        return jwtHelper.generateToken(userDto.getEmail());
    }
}
