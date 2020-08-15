package com.yixue.loxc.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TBankCardEntity;
import com.yixue.loxc.pojo.entity.TRechargeEntity;
import com.yixue.loxc.pojo.entity.TWithdrawEntity;
import com.yixue.loxc.pojo.vo.RechargeVo;
import com.yixue.loxc.pojo.vo.StatetVo;
import com.yixue.loxc.pojo.vo.UserVo;
import com.yixue.loxc.pojo.vo.system.AuditVo;
import com.yixue.loxc.system.service.TBankCardService;
import com.yixue.loxc.system.service.TRechargeService;
import com.yixue.loxc.system.service.TWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/recharge")
@CrossOrigin
public class TRechargeController {

    @Autowired
    TBankCardService tBankCardService;

    @Autowired
    TRechargeService tRechargeService;

    @Autowired
    TWithdrawService tWithdrawService;

    /**
     * 查询转态
     *
     * @return beginDate: 2020-05-25 00:00:00
     * endDate: 2020-06-24 23:59:59
     * state: -1
     * userId: 738c1356ab784de78f1bac2c088bb866
     * currentPage: 1
     */
    @PostMapping("/query")
    public Result<Page> query(@RequestBody StatetVo statetVo)  {
        System.out.println("statetVo = " + statetVo);
        System.err.println("进入状态查询================");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ");
//        StatetVo statetVo = new StatetVo();
        if (EmptyUtils.isEmpty(statetVo.getState())) {
            statetVo.setState(-1);
        }
        int pageindex = 1;
        if (EmptyUtils.isEmpty(statetVo.getCurrentPage())) {
            statetVo.setCurrentPage(pageindex);  //当前页码
        }

//        statetVo.setUserId(userId); //当前用户id

        Page<TRechargeEntity> page = tRechargeService.selectAll(statetVo);

        if (page != null) {
            return new Result<Page>(200, "数据加载成功", page);
        }

        return new Result<Page>(300, "数据失败");
    }

    /**
     * 充值金额
     *
     * @param showAmount
     * @param amount
     * @param remark
     * @param userId
     * @param username
     * @param bankCardId
     * @return
     */
    @PostMapping("/add")
    public Result<TRechargeEntity> add(@RequestBody RechargeVo rechargeVo) {


        Boolean bool = false;
        //判断银行卡是那个银行
        TBankCardEntity entities = tBankCardService.bankmoney(rechargeVo.getUserId());

        //通过userId去查询银行卡余额
        //TWithdrawEntity tWithdrawEntity=tWithdrawService.selectbank(entities.getUserId(),entities.getRealname());
        if (entities.getBalance() >= rechargeVo.getAmount()) {
            bool = true;
        }
//        RechargeVo rechargeVo = new RechargeVo();
//        rechargeVo.setAmount(Long.parseLong(amount));
//        rechargeVo.setShowAmount(Long.parseLong(showAmount));
//        rechargeVo.setRemark(remark);
//        rechargeVo.setUserId(userId);
//        rechargeVo.setUsername(username);
//        rechargeVo.setBankCardId(bankCardId);
        rechargeVo.setRealname(entities.getRealname());

        if (bool) {
            //对银行卡金额进行减少，对账户进行增加t_user_wallet
            int i = tBankCardService.updataBank(rechargeVo);
            return new Result(200, "充值中,等待审核!");
        } else {
            return new Result(300, "充值失败!");
        }
    }

    /**
     * 查询所有充值记录
     *
     * @return
     */

    public Result<TRechargeEntity> getRechargeAll(String id) {
        List<TRechargeEntity> list = tRechargeService.getAll(id);
        if (list != null) {
            return new Result(200, "加载成功", list);
        }
        return new Result(300, "加载失败");
    }


    @PostMapping("/audit")
    public Result audit(@RequestBody AuditVo auditVo) {

        int i = tRechargeService.audit(auditVo.getId(), auditVo.getState());
        if (i > 0) {
            return new Result(200, "审核成功");
        }
        return new Result(600, "审核失败");
    }

}
