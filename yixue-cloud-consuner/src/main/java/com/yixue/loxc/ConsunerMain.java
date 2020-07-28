package com.yixue.loxc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ConsunerMain {

    public static void main(String[] args) {
        SpringApplication.run(ConsunerMain.class,args);
    }
}
