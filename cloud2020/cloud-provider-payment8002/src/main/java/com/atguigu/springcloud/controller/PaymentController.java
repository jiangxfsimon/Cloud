package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.impl.PaymentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentServiceImpl paymentService;
    @Value("${server.port}")
    private String port;

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
}
