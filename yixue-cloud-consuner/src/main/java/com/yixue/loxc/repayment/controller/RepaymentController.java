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
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/finance/repayment")
public class RepaymentController {

    private static final String PAYMENT_URL = "http://localhost:8088";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/query")
    public Result getRepaymentList(QueryObject queryObject) {
        return restTemplate.postForObject(PAYMENT_URL + "/finance/repayment/query", queryObject, Result.class);
    }

    @RequestMapping("/getByBorrowId")
    @ResponseBody
    public Result getByBorrowId(String borrowId) {
        return restTemplate.postForObject(PAYMENT_URL + "/finance/repayment/getByBorrowId", borrowId, Result.class);
    }

    @RequestMapping("/repay")
    @ResponseBody
    public Result repay(String id, String userId) {
        TRepayment tRepayment = new TRepayment();
        tRepayment.setId(id);
        tRepayment.setBorrowUserId(userId);
        return restTemplate.postForObject(PAYMENT_URL + "/finance/repayment/repay", tRepayment, Result.class);
    }

    @RequestMapping("/detail/query")
    public Result getRepaymentDetailList(QueryObject queryObject) {
        return restTemplate.postForObject(PAYMENT_URL + "/finance/repayment/detail/query", queryObject, Result.class);
    }
}
