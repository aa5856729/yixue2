package com.yixue.loxc.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TSystemDictionaryEntity;
import com.yixue.loxc.pojo.vo.PageRelust;
import com.yixue.loxc.pojo.vo.system.DictionaryVo;
import com.yixue.loxc.pojo.vo.system.SystemVo;
import com.yixue.loxc.system.service.DictionaryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/dictionary")
@CrossOrigin
public class DictionaryController {

    @Resource
    DictionaryService dictionaryService;

    @PostMapping("/query")
    public Result<TSystemDictionaryEntity> query(@RequestBody SystemVo systemVo) {
//        String key = null;
//        if (EmptyUtils.isNotEmpty(keyword)) {
//            key = keyword;
//        }
        int indexPage = 1;
        if (EmptyUtils.isNotEmpty(systemVo.getCurrentPage())) {
            indexPage = systemVo.getCurrentPage();
        }
        IPage<TSystemDictionaryEntity> list = dictionaryService.selectKey(systemVo.getKeyword(), indexPage);
        if (EmptyUtils.isNotEmpty(list.getRecords())) {
            PageRelust pageRelust = new PageRelust();
            pageRelust.setListData(list.getRecords());
            pageRelust.setTotalPage((int) list.getPages());
            pageRelust.setCurrentPage((int) list.getCurrent());
            System.err.println("==========" + list.getRecords());
            return new Result(200, "数据加载成功", pageRelust);
        } else {
            return new Result(200, "数据加在失败");
        }
    }

    @PostMapping("/add")
    public Result add(@RequestBody DictionaryVo dictionaryVo) {
        int i = dictionaryService.save(dictionaryVo.getName(), dictionaryVo.getCode());
        if (i > 0) {
            return new Result(200, "添加成功");
        }
        return new Result(600, "数据添加失败");
    }

    @PostMapping("/update")
    public Result update(@RequestBody DictionaryVo dictionaryVo) {
        int i = dictionaryService.update(dictionaryVo.getId(), dictionaryVo.getName(), dictionaryVo.getCode());
        if (i > 0) {
            return new Result(200, "修改成功");
        }
        return new Result(200, "修改失败");
    }

    @PostMapping("/getAll")
    public Result getAll() {
        List<TSystemDictionaryEntity> list = dictionaryService.getAll();
        if (EmptyUtils.isNotEmpty(list)) {
            return new Result(200, "数据加载成功", list);
        }
        return new Result(200, "数据加载失败");
    }


}
