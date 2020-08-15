package com.yixue.loxc.system.controller;

import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TLoginLogEntity;
import com.yixue.loxc.pojo.entity.TRechargeEntity;
import com.yixue.loxc.pojo.entity.TSystemDictionaryEntity;
import com.yixue.loxc.pojo.entity.TSystemDictionaryItemEntity;
import com.yixue.loxc.pojo.vo.RechargeVo;
import com.yixue.loxc.pojo.vo.StatetVo;
import com.yixue.loxc.pojo.vo.system.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.ParseException;

@RestController
@CrossOrigin
@RequestMapping("/system")
public class SystemController {
    private static final String PAYMENT_URL="http://localhost:9002";

    @Resource
    RestTemplate restTemplate;

    @PostMapping("/dictionary/query")
    public Result<TSystemDictionaryEntity> query(SystemVo systemVo) {
        return restTemplate.postForObject(PAYMENT_URL+"/dictionary/query",systemVo,Result.class);
    }

    @PostMapping("/dictionary/add")
    public Result add(DictionaryVo dictionaryVo) {
        return restTemplate.postForObject(PAYMENT_URL+"/dictionary/add",dictionaryVo,Result.class);
    }

    @PostMapping("/dictionary/update")
    public Result update(DictionaryVo dictionaryVo) {
        return restTemplate.postForObject(PAYMENT_URL+"/dictionary/update",dictionaryVo,Result.class);
    }

    @PostMapping("/dictionary/getAll")
    public Result getAll() {
        return restTemplate.getForObject(PAYMENT_URL+"/dictionary/getAll",Result.class );
    }

    @PostMapping("/dictionaryItem/query")
    public Result dictionaryItemquery(SystemVo systemVo) {
        return restTemplate.postForObject(PAYMENT_URL+"dictionaryItem/query",systemVo,Result.class);
    }

    @PostMapping("/dictionaryItem/add")
    public Result addDictionaryItem(DictionaryItemVo dictionaryItemVo) {
        return restTemplate.postForObject(PAYMENT_URL+"dictionaryItem/add",dictionaryItemVo,Result.class);
    }

    @PostMapping("/dictionaryItem/update")
    public Result dictionaryItemupdate(DictionaryItemVo dictionaryItemVo) {
        return restTemplate.postForObject(PAYMENT_URL+"dictionaryItem/update",dictionaryItemVo,Result.class);
    }

    /**
     * 列表
     */
    @GetMapping("/dictionaryItem/getAll")
    public Result<TSystemDictionaryItemEntity> getSystemDictionaryAll() {
        return restTemplate.getForObject(PAYMENT_URL+"/dictionaryItem/getAll",Result.class);
    }

    @PostMapping("/dictionaryItem/updataitem")
    public Result<TSystemDictionaryItemEntity> UpdataItem(TSystemDictionaryItemEntity systemDictionaryItemEntity){
        return restTemplate.postForObject(PAYMENT_URL+"/dictionaryItem/updataitem",systemDictionaryItemEntity,Result.class);
    }

    @PostMapping("/loginlog/query")
    public Result<TLoginLogEntity> querylog(TloginLogVo tloginLogVo) throws ParseException {
        System.out.println("======================"+tloginLogVo);
        return restTemplate.postForObject(PAYMENT_URL+"/loginlog/query",tloginLogVo,Result.class);
    }
    @PostMapping("/recharge/query")
    public Result<Page> query(StatetVo statetVo)  {
        return restTemplate.postForObject(PAYMENT_URL+"/recharge/query",statetVo,Result.class);
    }

    @PostMapping("/recharge/add")
    public Result<TRechargeEntity> add(RechargeVo rechargeVo) {
        return restTemplate.postForObject(PAYMENT_URL+"/recharge/add",rechargeVo,Result.class);
    }

    @PostMapping("/recharge/audit")
    public Result audit(AuditVo auditVo) {
        return restTemplate.postForObject(PAYMENT_URL+"/recharge/audit",auditVo,Result.class);
    }

}
