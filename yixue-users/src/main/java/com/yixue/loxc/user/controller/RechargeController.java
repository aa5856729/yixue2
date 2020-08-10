package com.yixue.loxc.user.controller;

import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TRechargeEntity;
import com.yixue.loxc.pojo.vo.LiuShuiVo;
import com.yixue.loxc.user.service.TRechargeService;
import com.yixue.loxc.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@CrossOrigin
public class RechargeController {

    @Resource
    TRechargeService tRechargeService;

    @PostMapping("/accountflow/query")
    public Result<TRechargeEntity> query(@RequestBody UserVO userVO) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        LiuShuiVo liuShuiVo=new LiuShuiVo();
        liuShuiVo.setUserId(userVO.getUserId());
        if (userVO.getBeginDate()!=null){
            liuShuiVo.setBeginDate(dateFormat.parse(userVO.getBeginDate()));
            liuShuiVo.setEndDate(dateFormat.parse(userVO.getEndDate()));
            liuShuiVo.setCurrentPage(Integer.parseInt(userVO.getCurrentPage()));
        }

         Page<TRechargeEntity> rechargeEntityList=tRechargeService.selectRecharge(liuShuiVo);
         if (rechargeEntityList !=null){
             return new Result(200,"数据加载成功",rechargeEntityList);
         }
        return new Result(200,"数据加载失败");
    }
}
