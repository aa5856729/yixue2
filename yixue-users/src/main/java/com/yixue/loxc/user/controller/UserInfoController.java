package com.yixue.loxc.user.controller;


import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TUserInfoEntity;
import com.yixue.loxc.pojo.system.SystemDictionaryItemEntity;

import jdk.nashorn.internal.objects.annotations.Constructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import com.yixue.loxc.user.service.UserInfoService;

import java.io.File;
import java.util.Date;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-05 16:11:11
 */
@RestController
@RequestMapping("/user/userinfo")
@CrossOrigin
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/get/{id}")
    public Result<TUserInfoEntity> getinfo(@PathVariable("id") String id) {
        TUserInfoEntity data = userInfoService.getinfoId(id);
        if (data != null) {
            System.out.println("已加载数据"+data);
            return new Result(200, "加载成功", data);
        }
        return new Result(400, "数据不存在");
    }

    @PostMapping("/update")
    public Result<TUserInfoEntity> update(@RequestParam String file,@RequestParam String avatar,
                                         @RequestParam String accountId,@RequestParam String realname,
                                         @RequestParam String idCardNumber,@RequestParam String phoneNumber,
                                         @RequestParam String eduBackgroundId,@RequestParam String incomeLevelId,
                                         @RequestParam String marriageId,@RequestParam String houseConditionId
                                         ) {
        System.out.println(file);
        TUserInfoEntity userInfoEntity=new TUserInfoEntity();
        userInfoEntity.setAvatar(avatar);
        userInfoEntity.setAccountId(accountId);
        userInfoEntity.setEduBackgroundId(Integer.parseInt(eduBackgroundId));
        userInfoEntity.setHouseConditionId(Integer.parseInt(houseConditionId));
        userInfoEntity.setIdCardNumber(phoneNumber);
        userInfoEntity.setRealname(realname);
        userInfoEntity.setIdCardNumber(idCardNumber);
        userInfoEntity.setIncomeLevelId(Integer.parseInt(incomeLevelId));
        userInfoEntity.setMarriageId(Integer.parseInt(marriageId));
        userInfoEntity.setCreateTime(new Date());
        System.out.println(userInfoEntity);
        Integer i = userInfoService.Userupdate(userInfoEntity);
        System.err.println(i);
        if (i>0){
            return new Result(200,"更新成功!");
        }
        return new Result(202,"更新失败!");
    }

    /**
     * 上传头像
     * @param id
     * @param name
     * @return
     */
    @PostMapping("/uploadAvatar")
    //TODO 未完成
    public Result<TUserInfoEntity> uploadAvatar(@RequestParam String id, @RequestParam String name){
        System.out.println(id);
        System.out.println(name);
        String path="F:yixue/06-yixie-project/front/avatar";  //绝对路径


        File file=new File(path);
        //file.compareTo();
        System.out.println(file);

        return null;
    }

}
