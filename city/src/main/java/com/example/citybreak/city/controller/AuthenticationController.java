package com.example.citybreak.city.controller;

import com.example.citybreak.city.config.JwtService;
import com.example.citybreak.city.dto.AuthenticationResponseDto;
import com.example.citybreak.city.dto.CreateUserDto;
import com.example.citybreak.city.dto.LoginUserDto;
import com.example.citybreak.city.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponseDto> signup(
            @Valid @RequestBody CreateUserDto input
            ) {
        AuthenticationResponseDto response = authenticationService.signup(input);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> authenticate(
            @Valid @RequestBody LoginUserDto input
    ) {
        AuthenticationResponseDto response = authenticationService.authenticate(input);
        return ResponseEntity.ok(response);
    }



}
