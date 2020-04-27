package com.atuigu.springcloud.controller;


import com.atuigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentHystrixController {
    @Autowired
    private PaymentService paymentService;
    @GetMapping("/payment_ok")
    public String payment_ok(){
        System.out.println("one time...ok");
        return paymentService.payment_ok();
    }
    @HystrixCommand(fallbackMethod = "paymentTimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")})
    @GetMapping("/payment_timeout")
    public String payment_timeout(){
        System.out.println("one time...timeout");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return paymentService.payment_timeout();
    }
    public String paymentTimeOutHandler(){
        return Thread.currentThread().getName()+" paymentTimeOutHandler,server is busy,please try again later....";
    }
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        return result;
    }
}
