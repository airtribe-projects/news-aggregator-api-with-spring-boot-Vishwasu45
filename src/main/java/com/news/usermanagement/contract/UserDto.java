package com.news.usermanagement.contract;

import lombok.Data;

@Data
public class UserDto {

    private String email;

    private String password;

    private String fullName;
}
