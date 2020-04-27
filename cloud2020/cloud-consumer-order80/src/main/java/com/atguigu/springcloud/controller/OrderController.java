package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.loadbalance.MyIRuleImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
//    public static final String PAYMENT_URL="http://localhost:8001";
    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";
    @Autowired//Spring提供的用于访问Rest服务的客户端工具集
    private RestTemplate restTemplate;
    @Autowired
    private MyIRuleImpl myIRuleImpl;
    @Autowired
    private DiscoveryClient discoveryClient;

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
    @GetMapping("/consumer/payment/getEntity/{id}")
    public Object getPayment2(@PathVariable("id")Long id) {
     ResponseEntity entity=restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommResult.class);
     if(entity.getStatusCode().is2xxSuccessful()){
         System.out.println(entity.getHeaders());
         return entity.getBody();
     }else {
         return new CommResult(444,"操作失败");
     }
    }

    @GetMapping("/loadbalance")
    public String loadBalance(){
        URI uri = myIRuleImpl.getInstance(discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE")).getUri();
        System.out.println(uri);
        return restTemplate.getForObject(uri+"/loadbalance",String.class);
    }
}
