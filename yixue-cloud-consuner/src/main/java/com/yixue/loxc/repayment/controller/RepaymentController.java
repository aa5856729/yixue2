package com.yixue.loxc.repayment.controller;

import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.TRepayment;
import com.yixue.loxc.vo.QueryObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/finance/repayment")
public class RepaymentController {

    private static final String PAYMENT_URL = "http://localhost:8088";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/query")
    public Result getRepaymentList(QueryObject queryObject) {
        return restTemplate.postForObject(PAYMENT_URL + "/repayment/query", queryObject, Result.class);
    }

    @RequestMapping("/getByBorrowId")
    public Result getByBorrowId(String borrowId) {
        return restTemplate.postForObject(PAYMENT_URL + "/repayment/getByBorrowId", borrowId, Result.class);
    }

    @RequestMapping("/repay")
    public Result repay(String id, String userId) {
        TRepayment tRepayment = new TRepayment();
        tRepayment.setId(id);
        tRepayment.setBorrowUserId(userId);
        return restTemplate.postForObject(PAYMENT_URL + "/repayment/repay", tRepayment, Result.class);
    }

    @RequestMapping("/detail/query")
    public Result getRepaymentDetailList(QueryObject queryObject) {
        return restTemplate.postForObject(PAYMENT_URL + "/repayment/detail/query", queryObject, Result.class);
    }
}
