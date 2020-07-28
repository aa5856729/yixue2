package com.yixue.loxc.bid.controller;

import com.yixue.loxc.bid.service.TBidService;
import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.TBidEntity;
import com.yixue.loxc.vo.QueryObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bid")
@CrossOrigin
public class TBidController {

    @Resource
    private TBidService tBidService;

    @PostMapping("/query")
    public Result<Page> query(@RequestBody QueryObject queryObject){
        System.out.println("/////////////");
        return getPage(queryObject);
    }

    @GetMapping("/getByBorrowId/{borrowId}")
    public Result<Page> GetByBorrowId(@PathVariable String borrowId){
        QueryObject queryObject = new QueryObject();
        queryObject.setBorrowId(borrowId);
        return getPage(queryObject);
    }

    @PostMapping("/add")
    public Result<Object> addTBid(@RequestBody TBidEntity tBidEntity){

        if(tBidService.insertBid(tBidEntity)){
            return new Result<>(200,"投标成功！");
        }
        return new Result<>(400,"错误，投标失败");
    }

    /**
     * 获取查询数据
     * @param queryObject   查询条件以及分页条件
     * @return
     */
    private Result<Page> getPage(QueryObject queryObject){
        Map<String,Object> param = new HashMap<>();

        if (queryObject != null){
            if (queryObject.getBeginDate()!= null && queryObject.getEndDate() != null ){
                param.put("beginDate",queryObject.getBeginDate().toString());
                param.put("endDate",queryObject.getEndDate().toString());
            }
        }else{
            return null;
        }
        if(!"".equals(queryObject.getUserId()) && !(null == queryObject.getUserId())){
            param.put("bidUserId",queryObject.getUserId());
        }
        if("1,20,30,31,40,50".equals(queryObject.getBorrowStates())){
            param.put("borrowState",null);
        }else{
            param.put("borrowState",queryObject.getBorrowStates());
        }
        if(!"".equals(queryObject.getBorrowId()) && !(null == queryObject.getBorrowId())) {
            param.put("borrowId", queryObject.getBorrowId());
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
