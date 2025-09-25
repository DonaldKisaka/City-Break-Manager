package com.example.citybreak.city.service;

import com.example.citybreak.city.config.JwtService;
import com.example.citybreak.city.dto.AuthenticationResponseDto;
import com.example.citybreak.city.dto.CreateUserDto;
import com.example.citybreak.city.dto.LoginUserDto;
import com.example.citybreak.city.model.User;
import com.example.citybreak.city.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthenticationService (UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthenticationResponseDto signup(CreateUserDto input) {


            if (userRepository.findByEmail(input.email()).isPresent()) {
                throw new RuntimeException("Email already exists");
            }

            User user = new User(
                    input.email(),
                    passwordEncoder.encode(input.password()),
                    input.username()
            );

            user.setEnabled(true);
            userRepository.save(user);

            String jwtToken = jwtService.generateToken(user);

            return new AuthenticationResponseDto(jwtToken, user.getUsername(), user.getEmail());

    }

    public AuthenticationResponseDto authenticate(LoginUserDto input) {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.email(),
                            input.password()
                    )
            );

            User user = userRepository.findByEmail(input.email()).orElseThrow(() -> new RuntimeException("User not found"));

            String jwtToken = jwtService.generateToken(user);

            return new AuthenticationResponseDto(jwtToken, user.getUsername(), user.getEmail());


    }



}
