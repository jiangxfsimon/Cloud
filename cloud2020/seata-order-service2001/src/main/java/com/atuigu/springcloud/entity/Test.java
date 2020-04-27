package com.atuigu.springcloud.entity;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;

import javax.annotation.PostConstruct;

public class Test {
    private long workId=0;//范围0-31
    private long datacenterId=1;//范围0-31
    private Snowflake snowflake= IdUtil.createSnowflake(workId,datacenterId);
//    @PostConstruct
    public void init(){
        try {
            workId= NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        }catch (Exception e){
            e.printStackTrace();
            workId=NetUtil.getLocalhostStr().hashCode();
        }
    }
    public synchronized long snowflakeId(){
        init();
        return snowflake.nextId();
    }
    public static void main(String[] args) {
        Test test = new Test();
        for (int i = 0; i < 10; i++) {
            System.out.println(test.snowflakeId());
        }
    }
}
