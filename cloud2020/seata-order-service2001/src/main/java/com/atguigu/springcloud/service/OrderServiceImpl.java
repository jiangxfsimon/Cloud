package com.atguigu.springcloud.service;

import com.atguigu.springcloud.dao.OrderDao;
import com.atuigu.springcloud.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private StorageService storageService;
    @Autowired
    private AccountService accountService;

    @Override
    public void create(Order order) {
        orderDao.create(order);
        storageService.decrease(order.getProductId(),order.getCount());
        accountService.decrease(order.getUserId(),order.getMoney());
        System.out.println("修改订单状态");
        orderDao.update(order.getUserId(),0);
    }
}
