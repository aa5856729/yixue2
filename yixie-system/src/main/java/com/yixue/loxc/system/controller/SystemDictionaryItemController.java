package com.yixue.loxc.system.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TSystemDictionaryItemEntity;
import com.yixue.loxc.system.service.SystemDictionaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-06-12 10:00:41
 */
@RestController
@RequestMapping("/system/dictionaryItem")
@CrossOrigin
public class SystemDictionaryItemController {
    @Autowired
    private SystemDictionaryItemService SystemDictionaryItemService;

    /**
     * 列表
     */
    @GetMapping("/getAll")
    public Result<TSystemDictionaryItemEntity> getAll(){
        System.out.println("进入Ststem===================");
        List<TSystemDictionaryItemEntity> systemDictionaryItemList=SystemDictionaryItemService.getAll();
        System.out.println(systemDictionaryItemList);
        if (systemDictionaryItemList!=null){
            return new Result(200,"加载成功",systemDictionaryItemList);
        }
        return new Result(202,"数据加载失败");
    }


    /**
     * 修改数据类型
     */
    @PostMapping("/updataitem")
    public Result<TSystemDictionaryItemEntity>  UpdataItem(@RequestBody TSystemDictionaryItemEntity systemDictionaryItemEntity){

        Integer i=SystemDictionaryItemService.updataItem(systemDictionaryItemEntity);
        if (i>0){
            return new Result(200,"修改成功");
        }else {
            return new Result(202,"修改失败");
        }
    }
}
