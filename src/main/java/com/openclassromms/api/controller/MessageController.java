package com.openclassromms.api.controller;

import com.openclassromms.api.model.MessageRequest;
import com.openclassromms.api.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/")
    public String sendMessage(@RequestBody MessageRequest request){
       return messageService.sendMessage(request);
    }
}
