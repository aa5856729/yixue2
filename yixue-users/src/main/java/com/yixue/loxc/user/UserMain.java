package com.yixue.loxc.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableFeignClients(basePackages ="com.yixue.loxc.user.openfeignsystrm")
@SpringBootApplication
//@EnableDiscoveryClient
@MapperScan(basePackages = "com.yixue.loxc.user.dao")
public class UserMain {
    public static void main(String[] args) {
        SpringApplication.run(UserMain.class,args);
    }
}
