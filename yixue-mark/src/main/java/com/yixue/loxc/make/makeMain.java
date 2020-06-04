package com.yixue.loxc.make;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class makeMain {

    public static void main(String[] args) {
        SpringApplication.run(makeMain.class,args);
    }
}
