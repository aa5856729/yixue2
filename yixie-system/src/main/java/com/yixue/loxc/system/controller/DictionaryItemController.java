package com.yixue.loxc.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yixue.loxc.commons.EmptyUtils;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.entity.TSystemDictionaryItemEntity;
import com.yixue.loxc.pojo.vo.PageRelust;
import com.yixue.loxc.pojo.vo.system.DictionaryItemVo;
import com.yixue.loxc.pojo.vo.system.SystemVo;
import com.yixue.loxc.system.service.DictionaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/dictionaryItem")
public class DictionaryItemController {

    @Autowired
    DictionaryItemService dictionaryItemService;

    @PostMapping("/query")
    public Result dictionaryItemquery(@RequestBody SystemVo systemVo) {

        String key = null;
        if (EmptyUtils.isNotEmpty(systemVo.getKeyword())) {
            key = systemVo.getKeyword();
        }
        int pid = 0;
        if (EmptyUtils.isNotEmpty(systemVo.getParentId())) {
            pid = Integer.parseInt(systemVo.getParentId());
        }
        int indexPage = 1;
        if (EmptyUtils.isNotEmpty(systemVo.getCurrentPage())) {
            indexPage = systemVo.getCurrentPage();
        }
        IPage<TSystemDictionaryItemEntity> iPage = dictionaryItemService.selectAll(key, indexPage, pid);
        if (EmptyUtils.isNotEmpty(iPage.getRecords())) {
            PageRelust pageRelust = new PageRelust();
            pageRelust.setCurrentPage((int) iPage.getCurrent());
            pageRelust.setTotalPage((int) iPage.getPages());
            pageRelust.setListData(iPage.getRecords());
            return new Result(200, "数据加载成功", pageRelust);
        }
        return new Result(200, "数据加载失败");
    }


    @PostMapping("/add")
    public Result addDictionaryItem(@RequestBody DictionaryItemVo dictionaryItemVo) {
        int i = dictionaryItemService.save(dictionaryItemVo.getParentId(),dictionaryItemVo.getValue(),dictionaryItemVo.getOrderNo());
        if (i > 0) {
            return new Result(200, "新增成功");
        }
        return new Result(200, "新增失败");
    }

    @PostMapping("/update")
    public Result dictionaryItemupdate(@RequestBody DictionaryItemVo dictionaryItemVo) {
        int i = dictionaryItemService.amend(dictionaryItemVo.getId(), dictionaryItemVo.getParentId(), dictionaryItemVo.getValue(), dictionaryItemVo.getOrderNo());
        if (i > 0) {
            return new Result(200, "修改成功");
        }
        return new Result(600, "修改失败");
    }
}
