package com.atguigu.springcloud.loadbalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface MyIRule {
    ServiceInstance getInstance(List<ServiceInstance> instances);
}
