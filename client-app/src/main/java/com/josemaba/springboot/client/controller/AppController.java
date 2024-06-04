package com.josemaba.springboot.client.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josemaba.springboot.client.dto.Message;

@RestController
public class AppController {

    @GetMapping("/list")
    public List<Message> getMessages() {
        return Collections.singletonList(new Message("Hello World"));
    }

    @PostMapping("/create")
    public Message createMessage(@RequestBody Message message) {
        System.out.println("Mensage guardado: " + message.getMessage());
        return message;
    }

    @GetMapping("/authorized")
    public Map<String, String> authorized(@RequestParam String code) {
        return Collections.singletonMap("code", code);
    }

    @GetMapping("/public")
    public Map<String, String> publicEndpoint() {
        return Collections.singletonMap("message", "Hello from public endpoint");
    }
}