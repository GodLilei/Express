package com.myproject.demo.impl;

import com.myproject.demo.dao.ExpressProcessDao;
import com.myproject.demo.entity.ExpressProcess;
import com.myproject.demo.services.ExpressProcessServices;
import com.myproject.demo.utils.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ExpressProcessImpl {

    @Resource
    private ExpressProcessServices expressProcessServices;
    private Tool tools = new Tool();

    private void insertExpPro(ExpressProcess expressProcess){
        expressProcessServices.insertExpPro(expressProcess);
    }
    private void updateExpPro(ExpressProcess expressProcess){
        expressProcessServices.updateExpPro(expressProcess);
    }
    public List<ExpressProcess> expressDetail(ExpressProcess expressProcess){
        return expressProcessServices.expressDetail(expressProcess);
    }

    public void queryByWaybillNo(ExpressProcess expressProcess){
        String waybillNo = expressProcess.getWaybill_no();
        List<ExpressProcess> list = expressProcessServices.queryByWaybillNo(waybillNo);
        if (list.size() == 0){
            log.info("----->未检测到单号存在，执行insert操作" + expressProcess.getWaybill_no());
            expressProcess.setCreate_time(tools.nowDate());
            expressProcess.setUser(expressProcess.getUser().concat(tools.userDateString()));
            insertExpPro(expressProcess);
        }else {
            log.info("----->更新操作：" + expressProcess.getOperate());
            String operate = list.get(0).getOperate();
            expressProcess.setOperate(operate.concat(expressProcess.getOperate()));
            updateExpPro(expressProcess);
        }
    }
    public void delExpPro(String[] array){
        expressProcessServices.delExpPro(array);
    }
}
