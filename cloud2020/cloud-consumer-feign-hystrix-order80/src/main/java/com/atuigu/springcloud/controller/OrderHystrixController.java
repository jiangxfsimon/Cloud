package com.atuigu.springcloud.controller;

import com.atuigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "payment_globalFallBack")
public class OrderHystrixController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment_ok")
    public String payment_ok(){
        return paymentHystrixService.payment_ok();
    }

//    @HystrixCommand(fallbackMethod = "paymentTimeOutHandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")})
    @HystrixCommand
    @GetMapping("/payment_timeout")
    public String payment_timeout(){
        System.out.println("one time...timeout");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return paymentHystrixService.payment_timeout();
    }
    public String paymentTimeOutHandler(){
        return Thread.currentThread().getName()+" paymentTimeOutHandler,server is busy,please try again after 3s....";
    }
    public String payment_globalFallBack(){
        return "Global异常处理信息，请稍候再试";
    }
}
