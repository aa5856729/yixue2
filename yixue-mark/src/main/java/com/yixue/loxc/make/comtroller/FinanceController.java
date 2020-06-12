package com.yixue.loxc.make.comtroller;

import com.yixue.loxc.commons.Constants;
import com.yixue.loxc.commons.Page;
import com.yixue.loxc.make.service.BorrowService;
import com.yixue.loxc.pojo.Result;
import com.yixue.loxc.pojo.TBorrow;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/finance")
@CrossOrigin(origins = "*",maxAge = 3600)               //实现表单跨域请求
public class FinanceController {

    @Resource
    private BorrowService borrowService;


    
    @PostMapping("/borrow/query")
    public Result<TBorrow> BorrowQuery(String borrowStates,Integer currentPage){
        Map<String,Object> param = new HashMap<String,Object>();

        if(!borrowStates.equals("20,30,40,50")){
            param.put("borrowState",borrowStates);
        }

        Page page = borrowService.getTBorrowByList(param,currentPage,Constants.DEFAULT_PAGE_SIZE);

        if(null != page.getListData()){
            return new Result(200,"成功",page);
        }

        return new Result(222,"失败，没有查询到数据");
    }
}
