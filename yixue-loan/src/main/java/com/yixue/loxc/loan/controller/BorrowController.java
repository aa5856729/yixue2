package com.yixue.loxc.loan.controller;

import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.loan.service.BorrowService;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.TBorrowEntity;
import com.yixue.loxc.pojo.entity.TUserWalletEntity;
import com.yixue.loxc.vo.QueryObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/finance/borrow")
public class BorrowController {

    @Resource
    private BorrowService borrowService;

    @PostMapping("/add")
    @ResponseBody
    public Result<Object> add(TBorrowEntity tBorrow) {
        if (tBorrow == null) {
            return new Result(222, "错误，未能获取到表单数据");
        }
        try {
            if (borrowService.add(tBorrow)) {
                return new Result(200, "数据添加成功！");
            } else {
                return new Result(223, "数据添加失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(224, "报错，请通知管理员");
        }
    }

    @RequestMapping(value = "/query", produces = "application/json")
    @ResponseBody
    public Result<TBorrowEntity> getTBorrow(QueryObject queryObject) {
        Map<String, Object> param = new HashMap<String, Object>();

        if (!"".equals(queryObject.getUserId())) {
            param.put("borrowUserId", queryObject.getUserId());
        }

        if (!"1,10,11,20,30,31,40,50".equals(queryObject.getBorrowStates())) {
            param.put("borrowStates", queryObject.getBorrowStates());
        }

        if (queryObject != null) {
            if (queryObject.getBeginDate() != null && queryObject.getEndDate() != null) {
                param.put("beginDate", queryObject.getBeginDate().toString());
                param.put("endDate", queryObject.getEndDate().toString());
            }
        }

        Page page = borrowService.getTBorrowList(param, queryObject.getCurrentPage(), Constants.DEFAULT_PAGE_SIZE);
        if (null != page.getListData()) {
            return new Result(200, "成功", page);
        }
        return new Result(222, "失败，没有查询到数据");
    }

    @GetMapping("/get/{borrowId}")
    @ResponseBody
    public Result<TBorrowEntity> getBorrowInfo(@PathVariable String borrowId) {
        TBorrowEntity tBorrow = borrowService.getTBorrowByID(borrowId);
        if (tBorrow != null) {
            return new Result(200, "获取数据成功", tBorrow);
        } else {
            return new Result(223, "获取数据失败");
        }
    }

    @PostMapping("/audit")
    @ResponseBody
    public Result<Object> Audit(String borrowId, String borrowState) {
        if (borrowService.updateTBorrow(borrowId, borrowState)) {
            return new Result(200, "操作成功");
        } else {
            return new Result(222, "异常，操作失败！");
        }
    }

    @RequestMapping("/loan/audit")
    @ResponseBody
    public Result loanAudit(String borrowId, String borrowState) {
        if (borrowService.loanAudit(borrowId, borrowState)) {
            return new Result(200, "操作成功");
        } else {
            return new Result(222, "异常，操作失败！");
        }
    }
}
