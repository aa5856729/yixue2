package com.yixue.loxc.user.controller;

import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TRechargeEntity;
import com.yixue.loxc.pojo.vo.LiuShuiVo;
import com.yixue.loxc.user.service.TRechargeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("user/accountflow")
@CrossOrigin
public class RechargeController {

    @Resource
    TRechargeService tRechargeService;

    @PostMapping("/query")
    public Result<TRechargeEntity> query(@RequestParam("userId") String userId,@RequestParam(value = "beginDate",required = false) String beginDate,
                                         @RequestParam(value = "endDate",required = false) String endDate,
                                         @RequestParam(value = "currentPage",required = false) String currentPage ) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        LiuShuiVo liuShuiVo=new LiuShuiVo();
        liuShuiVo.setUserId(userId);
        if (beginDate!=null){
            liuShuiVo.setBeginDate(dateFormat.parse(beginDate));
            liuShuiVo.setEndDate(dateFormat.parse(endDate));
            liuShuiVo.setCurrentPage(Integer.parseInt(currentPage));
        }

         Page<TRechargeEntity> rechargeEntityList=tRechargeService.selectRecharge(liuShuiVo);
         if (rechargeEntityList !=null){
             return new Result(200,"数据加载成功",rechargeEntityList);
         }
        return new Result(200,"数据加载失败");
    }
}
