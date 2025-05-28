package com.openclassromms.api.controller;

import com.openclassromms.api.model.MessageRequest;
import com.openclassromms.api.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping()
    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody MessageRequest request) {
        messageService.sendMessage(request);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Message send with success");

        return ResponseEntity.ok(response);
    }
}
