package com.atuigu.springcloud.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentFallbakService implements PaymentHystrixService {
    @Override
    public String payment_ok() {return "Fallback payment_ok...";}

    @Override
    public String payment_timeout() {return "Fallback payment_timeout...";}
}
