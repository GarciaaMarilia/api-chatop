package com.openclassromms.api.service;

import com.openclassromms.api.model.UserDto;
import com.openclassromms.api.repository.UserRepository;
import exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserDto::fromUser)
                .orElseThrow(() -> new UserNotFoundException(
                        "User not found"
                ));
    }
}
