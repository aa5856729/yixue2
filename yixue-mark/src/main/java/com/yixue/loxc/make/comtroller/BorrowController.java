package com.yixue.loxc.make.comtroller;

import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.make.service.BorrowService;
import com.yixue.loxc.pojo.*;
import com.yixue.loxc.vo.QueryObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/finance/borrow")
@CrossOrigin(origins = "*",maxAge = 3600)               //实现表单跨域请求
public class BorrowController {

    @Resource
    private BorrowService borrowService;

    
    @PostMapping("/query")
    public Result<TBorrow> BorrowQuery(QueryObject queryObject){
        Map<String,Object> param = new HashMap<String,Object>();

        if(!"".equals(queryObject.getUserId())){
            param.put("borrowUserId",queryObject.getUserId());
        }

        if(!"20,30,40,50".equals(queryObject.getBorrowStates()) && !"1,10,11,20,30,31,40,50".equals(queryObject.getBorrowStates())){
            param.put("borrowState",queryObject.getBorrowStates());
        }
        if (queryObject != null){
            if (queryObject.getBeginDate()!= null && queryObject.getEndDate() != null){
                param.put("beginDate",queryObject.getBeginDate().toString());
                param.put("endDate",queryObject.getEndDate().toString());
            }
        }


        Page page = borrowService.getTBorrowByList(param,queryObject.getCurrentPage(),Constants.DEFAULT_PAGE_SIZE);

        if(null != page.getListData()){
            return new Result(200,"成功",page);
        }

        return new Result(222,"失败，没有查询到数据");
    }

    @GetMapping("/get/{borrowId}")
    public Result<TBorrowEntity> BorrowGetBorrowId(@PathVariable String borrowId){

        if(borrowId == null){
            return new Result<TBorrowEntity>(222,"失败，borrowId值为空");
        }

        TBorrowEntity tBorrow = borrowService.getBorrowById(borrowId);
        if(tBorrow == null){
            return new Result<TBorrowEntity>(223,"失败，没有查询到该数据");
        }

        return new Result<TBorrowEntity>(200,"成功查询到该数据",tBorrow);

    }

//    @PostMapping("/add")
//    public Result<Object> add(TBorrowEntity tBorrow){
//        if(tBorrow == null){
//            return new Result(222,"错误，未能获取到表单数据");
//        }
//        try{
//            if (borrowService.setTBorrow(tBorrow)){
//                return new Result(200,"数据添加成功！");
//            }else{
//                return new Result(223,"数据添加失败！");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            return new Result(224,"报错，请通知管理员");
//        }
//    }
//
    @PostMapping("/audit")
    public Result<Object> Audit(@RequestParam("borrowId")String borrowId,@RequestParam("borrowState")String borrowState){

        if(borrowService.updateTBorrow(borrowId,borrowState)){
            return new Result(200,"操作成功");
        }else{
            return new Result(222,"异常，操作失败！");
        }
    }
}