package com.openclassromms.api.service;

import com.openclassromms.api.model.Message;
import com.openclassromms.api.model.MessageRequest;
import com.openclassromms.api.model.Rental;
import com.openclassromms.api.model.User;
import com.openclassromms.api.repository.MessageRepository;
import com.openclassromms.api.repository.RentalsRepository;
import com.openclassromms.api.repository.UserRepository;
import exception.RentalNotFoundException;
import exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentalsRepository rentalsRepository;

    public String sendMessage(MessageRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User doesn't exists"));

        Rental rental = rentalsRepository.findById(request.getRentalId())
                .orElseThrow(() -> new RentalNotFoundException( "Rental doesn't exists"));

        Message message = new Message();
        message.setMessage(request.getMessage());
        message.setUser(user);
        message.setRental(rental);

        messageRepository.save(message);
        return "Message sent with success";
    }
}
