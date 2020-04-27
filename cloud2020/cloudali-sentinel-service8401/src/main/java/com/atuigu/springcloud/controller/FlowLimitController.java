package com.atuigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommResult;
import com.atguigu.springcloud.entities.Payment;
import com.atuigu.springcloud.handler.GlobalBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        return Thread.currentThread().getName()+"------testA";
    }
    @GetMapping("/testB")
    public String testB(){
        return Thread.currentThread().getName()+"------testB";
    }
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "dealHandler_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2){
        return "-------Hotkey";
    }
    public String dealHandler_testHotKey(String p1,String p2){
        return "-------deal_testHotKey";//sentinel系统默认提示：Blocked by Sentinel(flow limiting)
    }
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandlerClass = GlobalBlockHandler.class,blockHandler = "handleException1")
    public CommResult byResource(){
        return new CommResult(200,"按资源名限流",new Payment(2020L,"serial003"));
    }
    public CommResult handleException(BlockException exception){
        return new CommResult(444,exception.getClass().getCanonicalName()+"\t服务不可用");
    }

}
