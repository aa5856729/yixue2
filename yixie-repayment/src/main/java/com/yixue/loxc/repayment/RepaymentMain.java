package com.yixue.loxc.repayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.yixue.loxc.repayment", "com.yixue.loxc.user"})
public class RepaymentMain {
    public static void main(String[] args) {
        SpringApplication.run(RepaymentMain.class);
    }
}
