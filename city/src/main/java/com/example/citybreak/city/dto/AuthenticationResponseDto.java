package com.example.citybreak.city.dto;

public record AuthenticationResponseDto(
        String token,
        String username,
        String email
){}
