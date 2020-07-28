package com.yixue.loxc.make.controller;

import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.TBorrow;
import com.yixue.loxc.pojo.TBorrowEntity;
import com.yixue.loxc.vo.QueryObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/finance/borrow")
@CrossOrigin
public class MakeController {

    private static final String PAYMENT_URL = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/query")
    public Result<TBorrow> BorrowQuery(QueryObject queryObject){
                //***ForObject方法传递是参数为JSON格式   接收需添加@RequestBody
        return restTemplate.postForObject(PAYMENT_URL + "/borrow/query",queryObject,Result.class);
    }

    @GetMapping("/get/{borrowId}")
    public Result<TBorrowEntity> BorrowGetBorrowId(@PathVariable String borrowId){
        return restTemplate.getForObject(PAYMENT_URL + "/borrow/get/" + borrowId,Result.class);
    }

}
