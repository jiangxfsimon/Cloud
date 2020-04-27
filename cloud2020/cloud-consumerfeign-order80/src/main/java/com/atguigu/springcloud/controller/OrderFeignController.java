package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;
    @GetMapping("/payment/get/{id}")
    public CommResult getPaymentById(@PathVariable("id")Long id){
        return paymentFeignService.getPaymentById(id);
    }
    @GetMapping("/payment/feign/timeout")
    public String PaymentFeignTimeOut(){
        return paymentFeignService.PaymentFeignTimeOut();
    }
}
