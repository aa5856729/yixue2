package com.yixue.loxc.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TLoginLogEntity;
import com.yixue.loxc.pojo.vo.LoginLogVo;
import com.yixue.loxc.pojo.vo.PageRelust;
import com.yixue.loxc.pojo.vo.system.TloginLogVo;
import com.yixue.loxc.system.service.TLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-12 10:00:41
 */
@RestController
@RequestMapping("/loginlog")
@CrossOrigin
public class TLoginLogController {
    @Autowired
    private TLoginLogService tLoginLogService;

    /**
     * username=null, beginDate=null, endDate=null, loginResult=0, currentPage=0, accountType=0
     * @param tloginLogVo
     * @return
     * @throws ParseException
     */
    @PostMapping("/query")
    public Result<TLoginLogEntity> querylog(@RequestBody TloginLogVo tloginLogVo)  {
        System.out.println("====================="+tloginLogVo);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        LoginLogVo logVo = null;
        //管理员可以没有name,普通用户必须有   //accountType  全部-1  普通 1  后台 2
        //前台没有accountType,后台有accountType
        //前台带username，后台没有带username
        if (EmptyUtils.isNotEmpty(tloginLogVo.getUsername()) && EmptyUtils.isNotEmpty(tloginLogVo.getBeginDate()) && EmptyUtils.isNotEmpty(tloginLogVo.getEndDate()) && tloginLogVo.getLoginResult()!=0 && tloginLogVo.getCurrentPage()!=0 && tloginLogVo.getAccountType()!=0) { //前台带数据
            logVo = new LoginLogVo(tloginLogVo.getUsername(), tloginLogVo.getBeginDate(),tloginLogVo.getEndDate(), tloginLogVo.getLoginResult(), tloginLogVo.getCurrentPage());
        } else if (EmptyUtils.isNotEmpty(tloginLogVo.getUsername())) {
            if (tloginLogVo.getAccountType()!=0) {  //后台带数据查询
                logVo = new LoginLogVo();
                logVo.setUsername(tloginLogVo.getUsername());
                logVo.setBeginDate(tloginLogVo.getBeginDate());
                logVo.setEndDate(tloginLogVo.getEndDate());
                logVo.setLoginResult(tloginLogVo.getLoginResult());
                logVo.setAccountType(tloginLogVo.getAccountType());
            } else {  //前台初始化
                logVo = new LoginLogVo();
                logVo.setUsername(tloginLogVo.getUsername());
                logVo.setLoginResult(3);
            }
        } else if (EmptyUtils.isEmpty(tloginLogVo.getUsername()) && tloginLogVo.getAccountType()!=0 && tloginLogVo.getLoginResult()!=0) {
            logVo = new LoginLogVo();
            logVo.setBeginDate(tloginLogVo.getBeginDate());
            logVo.setEndDate(tloginLogVo.getEndDate());
            logVo.setLoginResult(tloginLogVo.getLoginResult());
            logVo.setAccountType(tloginLogVo.getAccountType());
        } else if (EmptyUtils.isEmpty(tloginLogVo.getUsername())) {
            //后台不带数据查询,查询全部
            logVo = new LoginLogVo();
            logVo.setLoginResult(3);
            if (tloginLogVo.getAccountType()==0) {
                logVo.setAccountType(3);
            } else {
                logVo.setAccountType(tloginLogVo.getAccountType());
                System.err.println(tloginLogVo.getAccountType());
            }
        }
        //List<TLoginLogEntity> list=tLoginLogService.allLog();

        int indexPage = 1;
        if (tloginLogVo.getCurrentPage()!=0) {
            indexPage = tloginLogVo.getCurrentPage();
        }
        IPage<TLoginLogEntity> page = tLoginLogService.userloglist(indexPage, logVo);
        if (page != null) {
            //创建一个实体类 把分页数据和集合数据放里面
            PageRelust pageRelust = new PageRelust();
            pageRelust.setCurrentPage((int) page.getCurrent());
            pageRelust.setTotalPage((int) page.getPages());  //总页数
            pageRelust.setListData(page.getRecords());
            return new Result(200, "数据加载成功", pageRelust);
        }
        return new Result(300, "数据加载失败");
    }


}
