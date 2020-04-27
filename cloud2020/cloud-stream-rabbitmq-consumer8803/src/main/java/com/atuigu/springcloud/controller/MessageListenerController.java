package com.atuigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
public class MessageListenerController {
    @Value("${server.port}")
    private String port;
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        System.out.println("消费者8803接收消息："+message.getPayload()+" port："+port);
    }
}
