package com.yixue.loxc.user.controller;

import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TWithdrawEntity;
import com.yixue.loxc.pojo.vo.WithdrawVo;
import com.yixue.loxc.user.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/withdraw")
@CrossOrigin
public class UserWithdrawController {

    @Autowired
    WithdrawService withdrawService;


    /**
     * 提现
     * @return
     *
     * bankName: 中国银行
     * branchName: 湖南郴州支行
     * cardNumber: 1234567890
     * realname: 建行
     * showAmount: 5000
     * amount: 500000
     * fee: 5000
     * userId: 738c1356ab784de78f1bac2c088bb866
     */
    @PostMapping("/add")
    public Result add(String bankName,String branchName,String cardNumber,String realname,
                      String showAmount,String amount,String fee,String userId){
        WithdrawVo withdrawVo=new WithdrawVo();
        withdrawVo.setAmount(Long.parseLong(amount));
        withdrawVo.setBankName(bankName);
        withdrawVo.setBranchName(branchName);
        withdrawVo.setCardNumber(cardNumber);
        withdrawVo.setRealname(realname);
        withdrawVo.setFee(Long.parseLong(fee));
        withdrawVo.setShowAmount(Long.parseLong(showAmount));
        withdrawVo.setUserId(userId);

        int i=withdrawService.add(withdrawVo);

        if (i>0){
             return new Result(200,"提现成功");
        }

        return new Result(300,"提现失败");
    }


}
