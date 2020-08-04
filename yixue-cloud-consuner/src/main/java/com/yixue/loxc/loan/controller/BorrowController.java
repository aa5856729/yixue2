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

@Controller
@CrossOrigin
@RequestMapping("/finance/borrow")
public class BorrowController {

    private static final String PAYMENT_URL = "http://localhost:8087";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/add")
    public Result<Object> add(TBorrowEntity tBorrow) {
        return restTemplate.postForObject(PAYMENT_URL + "/finance/borrow/add", tBorrow, Result.class);
    }

    @RequestMapping(value = "/query", produces = "application/json")
    public Result<TBorrowEntity> getTBorrow(QueryObject queryObject) {
        return restTemplate.postForObject(PAYMENT_URL + "/finance/borrow/query", queryObject, Result.class);
    }

    @GetMapping("/get/{borrowId}")
    @ResponseBody
    public Result<TBorrowEntity> getBorrowInfo(@PathVariable String borrowId) {
        return restTemplate.postForObject(PAYMENT_URL + "/finance/borrow/get/{borrowId}", borrowId, Result.class);
    }

    @PostMapping("/audit")
    @ResponseBody
    public Result<Object> Audit(TBorrowEntity tBorrowEntity) {
        return restTemplate.postForObject(PAYMENT_URL + "/finance/borrow/audit", tBorrowEntity, Result.class);
    }

    @RequestMapping("/loan/audit")
    public Result<Object> loanAudit(TBorrowEntity tBorrowEntity) {
        return restTemplate.postForObject(PAYMENT_URL + "/finance/borrow/loan/audit", tBorrowEntity, Result.class);
    }
}
