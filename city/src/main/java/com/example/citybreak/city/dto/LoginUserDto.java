package com.example.citybreak.city.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginUserDto (
        @NotBlank
        @Email(message = "Email should be valid")
        String email,

        @NotBlank
        String password
){}
