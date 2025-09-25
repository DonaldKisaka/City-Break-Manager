package com.example.citybreak.city.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDto(
        @NotBlank
        @Email(message = "Email should be valid")
        String email,

        @NotBlank
        String username,

        @NotBlank
        @Size(min = 8, message = "Password should be at least 8 characters")
        String password
) {}
