package com.openclassromms.api.service;

import com.openclassromms.api.model.LoginRequest;
import com.openclassromms.api.model.RegisterRequest;
import com.openclassromms.api.model.User;
import com.openclassromms.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public String register(RegisterRequest request){
        if(userRepository.existsByEmail((request.getEmail()))){
            return "E-mail ja cadastrado";
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return "Usuário registrado com sucesso!";
    }

    public String login (LoginRequest request){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getLogin(),
                            request.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtService.generateToken(request.getLogin());

            return token;
        }catch(AuthenticationException ex){
            throw new BadCredentialsException("Email ou senha inválidos.");
        }
    }
}
