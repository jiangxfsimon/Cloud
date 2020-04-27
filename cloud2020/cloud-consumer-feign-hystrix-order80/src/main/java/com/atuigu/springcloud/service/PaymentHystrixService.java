package com.atuigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient(value = "CLOUD-PROVIDE-HYSTRIX-PAYMENT",fallback = PaymentFallbakService.class)
public interface PaymentHystrixService {
    @GetMapping("/payment_ok")
    public String payment_ok();
    @GetMapping("/payment_timeout")
    public String payment_timeout();
}
