package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderZKController {
    @Autowired
    private RestTemplate restTemplate;
    public static final String URL="http://cloud-payment-service";

    @GetMapping("/consumer/payment/zk")
    public Object get(){
        return restTemplate.getForObject(URL+"/payment/zk",String.class);
    }
}
