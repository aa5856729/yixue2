package com.yixue.loxc.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TRechargeEntity;
import com.yixue.loxc.pojo.vo.LiuShuiVo;
import com.yixue.loxc.pojo.vo.PageRelust;
import com.yixue.loxc.user.service.TRechargeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/accountflow")
@CrossOrigin
public class RechargeController {

    @Resource
    TRechargeService tRechargeService;

    @PostMapping("/query")
    public Result<TRechargeEntity> query(@RequestBody LiuShuiVo liuShuiVo) throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
//        LiuShuiVo liuShuiVo = new LiuShuiVo();
//        liuShuiVo.setUserId(userId);
//        if (beginDate != null) {
//            liuShuiVo.setBeginDate(dateFormat.parse(beginDate));
//            liuShuiVo.setEndDate(dateFormat.parse(endDate));
//            liuShuiVo.setCurrentPage(Integer.parseInt(currentPage));
//        }

        IPage<TRechargeEntity> iPage = tRechargeService.selectRecharge(liuShuiVo);
        if (iPage.getRecords() != null) {
            PageRelust<TRechargeEntity> page=new PageRelust<>();
            page.setListData(iPage.getRecords());
            System.err.println("iPage.getCurrent()=="+iPage.getCurrent()+"===iPage.getSize()"+iPage.getSize()+"===iPage.getPages()"+iPage.getPages()+"====iPage.getTotal()="+iPage.getTotal());
            page.setCurrentPage((int)iPage.getCurrent());
            page.setTotalPage((int)iPage.getPages()==0?1:(int)iPage.getPages());
            return new Result(200, "数据加载成功", page);

        }
        return new Result(200, "数据加载失败");
    }
}
