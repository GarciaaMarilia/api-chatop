package com.openclassromms.api.service;

import com.openclassromms.api.model.Message;
import com.openclassromms.api.model.MessageRequest;
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
            message.setRentalId(request.getRentalId());
            message.setUserId(request.getUserId());

            messageRepository.save(message);
            return "Message send with success";
        } catch (Exception e) {
            throw new RuntimeException("Failed to send message", e);
        }
    }
}
