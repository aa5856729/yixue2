package com.yixue.loxc.bid.controller;

import com.yixue.loxc.bid.service.TBidService;
import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.vo.QueryObject;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/finance/bid")
@CrossOrigin
public class TBidController {

    @Resource
    private TBidService tBidService;

    @PostMapping("/query")
    public Result<Page> query(QueryObject queryObject){

        Map<String,Object> param = new HashMap<>();
        if(!"".equals(queryObject.getUserId())){
            param.put("bidUserId",queryObject.getUserId());
        }
        if("1,20,30,31,40,50".equals(queryObject.getBorrowStates())){
            param.put("borrowState",null);
        }else{
            param.put("borrowState",queryObject.getBorrowStates());
        }

        if (queryObject != null){
            if (queryObject.getBeginDate()!= null && queryObject.getEndDate() != null ){
                param.put("beginDate",queryObject.getBeginDate().toString());
                param.put("endDate",queryObject.getEndDate().toString());
            }
        }
        try{
            Page page = tBidService.getTBidByListPage(param,queryObject.getCurrentPage(),Constants.DEFAULT_PAGE_SIZE);
            if(null == page){
                return new Result<>(223,"没有查询到数据");
            }
            return new Result<>(200,"数据查询成功",page);
        }catch (Exception e){
            e.printStackTrace();
            return new Result<>(222,"数据查找异常，请联系管理员!");
        }

    }
}
