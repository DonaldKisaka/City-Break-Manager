package com.example.citybreak.city.service;

import com.example.citybreak.city.config.JwtService;
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

    public User signup(CreateUserDto input) {

        try {
            if (userRepository.findByEmail(input.email()).isPresent()) {
                throw new RuntimeException("Email already exists");
            }

            User user = new User(
                    input.email(),
                    passwordEncoder.encode(input.password()),
                    input.username()
            );

            user.setEnabled(true);

            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User authenticate(LoginUserDto input) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            input.email(),
                            input.password()
                    )
            );

            return userRepository.findByEmail(input.email()).orElseThrow(() -> new RuntimeException("User not found"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(User user) {
        return jwtService.generateToken(user);
    }

    public long getExpirationTime() {
        return jwtService.getExpirationTime();
    }

}
