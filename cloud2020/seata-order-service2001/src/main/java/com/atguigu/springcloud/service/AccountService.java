package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-account-service")
public interface AccountService {
    @PostMapping("/account/decrease")
    CommResult decrease(@RequestParam("userId")Long userId, @RequestParam("count") BigDecimal count);
}
