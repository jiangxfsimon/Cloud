package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommResult;
import com.atguigu.springcloud.service.OrderService;
import com.atuigu.springcloud.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/order/create")
    public CommResult create(Order order){
        orderService.create(order);
        return new CommResult(200,"订单创建成功");
    }
}
