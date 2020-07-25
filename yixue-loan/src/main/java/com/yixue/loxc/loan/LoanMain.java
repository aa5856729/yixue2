package com.yixue.loxc.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.yixue.loxc.loan", "com.yixue.loxc.user"})
@SpringBootApplication
public class LoanMain {

    public static void main(String[] args) { SpringApplication.run(LoanMain.class,args); }

}
