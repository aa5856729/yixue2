package com.yixue.loxc.repayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"com.yixue.loxc.repayment", "com.yixue.loxc.user", "com.yixue.loxc.loan"})
public class RepaymentMain {
    public static void main(String[] args) {
        SpringApplication.run(RepaymentMain.class);
    }
}
