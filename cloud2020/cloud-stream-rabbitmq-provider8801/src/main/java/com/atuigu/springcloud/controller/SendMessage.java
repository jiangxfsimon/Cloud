package com.atuigu.springcloud.controller;

import com.atuigu.springcloud.service.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessage {
    @Autowired
    private MessageProvider messageProvider;
    @GetMapping("/sendmessage")
    public String send(){
        return messageProvider.send();
    }
}
