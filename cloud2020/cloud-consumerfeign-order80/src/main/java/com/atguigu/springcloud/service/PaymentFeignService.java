package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value ="CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping("/payment/get/{id}")
    public CommResult getPaymentById(@PathVariable("id")Long id); //拷贝自8001或者8002的controller
    @GetMapping("/payment/feign/timeout")
    public String PaymentFeignTimeOut();
}

