package com.openclassromms.api.service;

import com.openclassromms.api.model.LoginRequest;
import com.openclassromms.api.model.RegisterRequest;
import com.openclassromms.api.model.User;
import com.openclassromms.api.model.UserDto;
import com.openclassromms.api.repository.UserRepository;
import exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public Map<String, String> register(RegisterRequest request) {
        try {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new RuntimeException("E-mail already exists");
                // Ou retorne um Map com erro, depende da sua API
            }

            User user = new User();
            user.setEmail(request.getEmail());
            user.setName(request.getName());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(user);

            // Gerar token JWT para o usuário recém-criado
            String token = jwtService.generateToken(request.getEmail());

            // Retornar token junto com mensagem, por exemplo:
            Map<String, String> response = new HashMap<>();
            response.put("message", "User registered successfully!");
            response.put("token", token);
            return response;

        } catch (Exception e) {
            throw new RuntimeException("Failed to register user", e);
        }
    }


    public Map<String, String> login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtService.generateToken(request.getEmail());

            return Collections.singletonMap("token", token);
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Invalid credentials.");
        }
    }

    public UserDto getUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null || !authentication.isAuthenticated()) {
                throw new RuntimeException("No authenticated user found");
            }

            String email = authentication.getName();

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            return UserDto.fromUser(user);

        } catch (Exception e) {
            throw new RuntimeException("Failed to get user", e);
        }
    }

}
