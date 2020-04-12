package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {
    public static final String PAYMENT_URL="http://localhost:8081";
    @Autowired//Spring提供的用于访问Rest服务的客户端工具集
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommResult<Payment> create(Payment payment){
        log.info("create from OrderMain80 to PaymentMain8081,Payment:"+payment);
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommResult<Payment> getPayment(@PathVariable("id")Long id){
        log.info("create from OrderMain80 to PaymentMain8081,id: "+id);
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommResult.class);
    }
}
