package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.atguigu.springcloud.service.impl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentServiceImpl paymentService;
    @Value("${server.port}")
    private String port;
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service:services){
            System.out.println(service);//cloud-payment-service,cloud-order-service
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:instances){
            System.out.println(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
            //CLOUD-PAYMENT-SERVICE	192.168.56.1	8002	http://192.168.56.1:8002
            //CLOUD-PAYMENT-SERVICE	192.168.56.1	8001	http://192.168.56.1:8001
        }
        return this.discoveryClient;
    }

    @PostMapping("/payment/create")
    public CommResult<Payment> create(@RequestBody Payment payment){//此处必须添加@RequestBody,不然数据库serial=null
        System.out.println("payment: "+payment);
        int result = paymentService.create(payment);
        log.info("****插入结果:"+result);
        if(result>0){
            return new CommResult(200,"插入成功,port: "+port,result);
        }else {
            return new CommResult(444,"插入失败",null);
        }
    }
    @GetMapping("/payment/get/{id}")
    public CommResult getPaymentById(@PathVariable("id")Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果:"+payment);
        if(null!=payment){
            return new CommResult(200,"查询成功,port: "+port,payment);
        }else {
            return new CommResult(444,"查询失败,id:"+id,null);
        }
    }
    @GetMapping("/loadbalance")
    public String loadbalance(){
        return port;
    }
    @GetMapping("/payment/feign/timeout")
    public String PaymentFeignTimeOut(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port;
    }
}
