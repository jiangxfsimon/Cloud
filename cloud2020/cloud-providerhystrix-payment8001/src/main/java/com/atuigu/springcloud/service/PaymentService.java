package com.atuigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PaymentService {


    public String payment_ok(){
        return "线程池："+Thread.currentThread().getName()+" payment_ok";
    }
    public String payment_timeout(){
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        int i=10/0;
        return "线程池："+Thread.currentThread().getName()+" payment_TimeOut";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸
    })//参考HystrixCommandProperties  【10秒内10次请求有60%失败就会启动熔断】
    public String paymentCircuitBreaker(Integer id){
        if(id<0)throw new RuntimeException("id不能为负数");
        return Thread.currentThread().getName()+" 调用成功.";
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return Thread.currentThread().getName()+"id不能为负数，请稍候再试.";
    }
}
