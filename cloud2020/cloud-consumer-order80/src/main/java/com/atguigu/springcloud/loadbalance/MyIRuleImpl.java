package com.atguigu.springcloud.loadbalance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyIRuleImpl implements MyIRule {
    private AtomicInteger atomicInteger=new AtomicInteger(0);

    @Override
    public ServiceInstance getInstance(List<ServiceInstance> instances) {
        return switchService(instances);
    }
    public ServiceInstance switchService(List<ServiceInstance> instances){
        int all=instances.size();
        System.out.println(all+"   ||||||||||||||");
        while (true){
            int current = atomicInteger.get();
            int next=(current+1)%all;
            if(atomicInteger.compareAndSet(current,next)){
                return instances.get(next);
            }
        }
    }
}
