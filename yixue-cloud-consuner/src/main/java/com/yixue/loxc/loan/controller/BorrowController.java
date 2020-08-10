package com.yixue.loxc.loan.controller;

import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.TBorrowEntity;
import com.yixue.loxc.vo.QueryObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/finance/borrow")
public class BorrowController {

    private static final String PAYMENT_URL = "http://localhost:8087";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/add")
    public Result<Object> add(TBorrowEntity tBorrow) {
        return restTemplate.postForObject(PAYMENT_URL + "/borrow/add", tBorrow, Result.class);
    }

    @PostMapping("/audit")
    public Result<Object> Audit(TBorrowEntity tBorrowEntity) {
        return restTemplate.postForObject(PAYMENT_URL + "/borrow/audit", tBorrowEntity, Result.class);
    }

    @RequestMapping("/loan/audit")
    public Result<Object> loanAudit(TBorrowEntity tBorrowEntity) {
        return restTemplate.postForObject(PAYMENT_URL + "/borrow/loan/audit", tBorrowEntity, Result.class);
    }
}
