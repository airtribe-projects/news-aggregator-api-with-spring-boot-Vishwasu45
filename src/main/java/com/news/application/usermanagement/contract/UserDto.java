package com.news.application.usermanagement.contract;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    private String fullName;
}
