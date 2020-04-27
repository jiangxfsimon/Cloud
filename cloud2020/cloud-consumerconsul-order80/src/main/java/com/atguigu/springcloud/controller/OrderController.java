package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {
    public static final String PAYMENT_URL="http://cloud-provider-payment";
    @Autowired//Spring提供的用于访问Rest服务的客户端工具集
    private RestTemplate restTemplate;

    @GetMapping("/consumer/consul")
    public Object create(Payment payment){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/consul",String.class);
    }
}
