package com.openclassromms.api.service;

import com.openclassromms.api.model.Message;
import com.openclassromms.api.model.MessageRequest;
import com.openclassromms.api.model.Rental;
import com.openclassromms.api.model.User;
import com.openclassromms.api.repository.MessageRepository;
import com.openclassromms.api.repository.RentalsRepository;
import com.openclassromms.api.repository.UserRepository;
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
        try {
            if (!userRepository.existsById(request.getUserId())) {
                return "User doesn't exists";
            }

            if (!rentalsRepository.existsById(request.getRentalId())) {
                return "Rental doesn't exists";
            }

            Message message = new Message();
            message.setMessage(request.getMessage());

            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Rental rental = rentalsRepository.findById(request.getRentalId())
                    .orElseThrow(() -> new RuntimeException("Rental not found"));

            message.setUser(user);
            message.setRental(rental);

            messageRepository.save(message);
            return "Message send with success";
        } catch (Exception e) {
            throw new RuntimeException("Failed to send message", e);
        }
    }
}
