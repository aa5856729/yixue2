package com.yixue.loxc.bid.controller;

import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.TBidEntity;
import com.yixue.loxc.vo.QueryObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/finance/bid")
public class BidController {

    @Resource
    private RestTemplate restTemplate;

    private static final String PAYMENT_URL = "http://localhost:8002";

    @PostMapping("/query")
    public Result<Page> query(QueryObject queryObject){
        return restTemplate.postForObject(PAYMENT_URL + "/bid/query",queryObject,Result.class);
    }

    @PostMapping("/getByBorrowId/{borrowId}")
    public Result<Page> GetByBorrowId(@PathVariable String borrowId){
        return restTemplate.getForObject(PAYMENT_URL + "/bid/getByBorrowId/" + borrowId,Result.class);
    }

    @PostMapping("/add")
    public Result<Object> addTBid(TBidEntity tBidEntity){
        return restTemplate.postForObject(PAYMENT_URL + "/bid/add",tBidEntity,Result.class);
    }
}
