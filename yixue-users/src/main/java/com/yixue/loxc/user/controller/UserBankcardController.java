package com.yixue.loxc.user.controller;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TBankCardEntity;
import com.yixue.loxc.pojo.entity.TWithdrawEntity;
import com.yixue.loxc.user.service.TBankCardService;
import com.yixue.loxc.user.service.WithdrawService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.Date;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/user/bankcard")
public class UserBankcardController {

    @Resource
    TBankCardService tBankCardService;

    /**
     * 绑定银行卡数据初始化
     * @param id
     * @return
     */
    @PostMapping("/get/{id}")
    public Result<TWithdrawEntity> get(@PathVariable String id){
        TBankCardEntity bankCardEntity= tBankCardService.getBank(id);

        //TWithdrawEntity tWithdrawEntity= withdrawService.getBank(id);
        if (bankCardEntity!=null){
            System.out.println("有数据绑定了银行卡");
            return new Result(200,"数据刷新成功",bankCardEntity);
        }else {
            System.out.println("没有绑定银行卡");
            return new Result(404,"没有绑定银行卡");
        }

    }

    /*
       绑定银行卡
     */
    //TODO 出现415错误 请求参数必须单个单个获取
    @PostMapping(value = "/add",consumes ="application/x-www-form-urlencoded")
    public Result<TWithdrawEntity> addBank( @RequestParam String userId,@RequestParam String realname,
                                            @RequestParam String cardNumber,@RequestParam String bankName,
                                            @RequestParam String branchName){
        System.out.println("进入了add");


        TBankCardEntity tBankCardEntity=new TBankCardEntity();
        tBankCardEntity.setBalance(1000L);
        tBankCardEntity.setBankName(bankName);
        tBankCardEntity.setBranchName(branchName);
        tBankCardEntity.setCardNumber(cardNumber);
        tBankCardEntity.setId(UUID.randomUUID().toString().substring(0,31));
        tBankCardEntity.setUserId(userId);
        tBankCardEntity.setCreateTime(new Date());

        Boolean bool= tBankCardService.addBank(tBankCardEntity);
        System.out.println(bool);
        if (bool){
            return new Result(200,"绑定成功");
        }
        return new Result(300,"绑定失败");
    }
}
