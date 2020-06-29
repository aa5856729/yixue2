package com.yixue.loxc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages = "com.yixue.loxc.system.dao")
public class System {
    public static void main(String []args){
        SpringApplication.run(System.class,args);
    }
}
