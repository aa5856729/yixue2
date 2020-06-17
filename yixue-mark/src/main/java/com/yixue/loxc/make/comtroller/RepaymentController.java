package com.yixue.loxc.make.comtroller;

import com.yixue.loxc.make.service.RepaymentService;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.TRepayment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/finance/repayment")
@CrossOrigin(origins = "*")             //实现表单跨域
public class RepaymentController {

    @Resource
    private RepaymentService repaymentService;

    @RequestMapping("/getByBorrowId")
    public Result<Object> getByBorrowId(String borrowId){
        System.out.println(borrowId);

        Map<String,Object> param = new HashMap<>();
        param.put("borrowId",borrowId);
        try{

            List<TRepayment> tRepaymentList = repaymentService.getTRepamentByList(param);

            if(tRepaymentList == null){
                return new Result(221,"失败，没有查询到数据！");
            }

            return new Result(200,"成功查询到数据",tRepaymentList);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(222,"查询数据出现错误！");
        }
    }
}
