package com.openclassromms.api.service;

import com.openclassromms.api.model.RegisterRequest;
import com.openclassromms.api.model.User;
import com.openclassromms.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public String register(RegisterRequest request){
        if(userRepository.existsByEmail((request.getEmail()))){
            return "E-mail ja cadastrado";
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        userRepository.save(user);

        return "Usuário registrado com sucesso!";
    }
}
