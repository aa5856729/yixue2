package com.yixue.loxc.repayment.controller;

import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.TRepayment;
import com.yixue.loxc.pojo.entity.TUserWalletEntity;
import com.yixue.loxc.repayment.service.RepaymentService;
import com.yixue.loxc.user.service.UserWalletService;
import com.yixue.loxc.vo.QueryObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/finance/repayment")
public class RepaymentController {

    @Resource
    private RepaymentService repaymentService;

    @Resource
    private UserWalletService userWalletService;

    @RequestMapping("/query")
    @ResponseBody
    public Result<Object> getRepaymentList(QueryObject queryObject) {
        Map<String, Object> param = new HashMap<String, Object>();

        if (!"".equals(queryObject.getUserId())) {
            param.put("borrowUserId", queryObject.getUserId());
        }

        if (!"20,30,40,50".equals(queryObject.getBorrowStates()) && !"1,10,11,20,30,31,40,50".equals(queryObject.getBorrowStates())) {
            param.put("borrowState", queryObject.getBorrowStates());
        }

        if (queryObject != null) {
            if (queryObject.getBeginDate() != null && queryObject.getEndDate() != null) {
                param.put("beginDate", queryObject.getBeginDate().toString());
                param.put("endDate", queryObject.getEndDate().toString());
            }
        }

        Page page = repaymentService.getRepaymentList(param, queryObject.getCurrentPage(), Constants.DEFAULT_PAGE_SIZE);
        if (null != page.getListData()) {
            return new Result(200, "获取数据成功", page);
        }
        return new Result(222, "获取数据失败");
    }

    @RequestMapping("/getByBorrowId")
    @ResponseBody
    public Result getByBorrowId(String borrowId) {
        List<TRepayment> tRepaymentList = repaymentService.getRepaymentByBorrowId(borrowId);
        if (tRepaymentList != null)
            return new Result(200, "获取数据成功", tRepaymentList);
        else
            return new Result(222, "获取数据失败");
    }

    @RequestMapping("/repay")
    @ResponseBody
    public Result repay(String id, String userId) {
        TRepayment tRepayment = repaymentService.getRepaymentById(id);
        TUserWalletEntity tUserWalletEntity = userWalletService.getwallet(userId);
        if (tUserWalletEntity.getAvailableAmount() > tRepayment.getTotalAmount()) {
            if (repaymentService.repay(tRepayment) > 0)
                return new Result(200, "还款成功");
            else
                return new Result(200, "还款失败，出现错误，请联系管理员解决");

        } else {
            return new Result(222, "还款失败");
        }
    }
}
