package com.myproject.demo.services;

import com.myproject.demo.dao.ExpressProcessDao;
import com.myproject.demo.entity.ExpressProcess;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExpressProcessServices {

    @Resource
    private ExpressProcessDao expressProcessDao;

    public void insertExpPro(ExpressProcess expressProcess){
        expressProcessDao.insertExpPro(expressProcess);
    }

    public List<ExpressProcess> queryByWaybillNo(String waybillNo){
        return expressProcessDao.queryByWaybillNo(waybillNo);
    };

    public void updateExpPro(ExpressProcess expressProcess){
        expressProcessDao.updateExpPro(expressProcess);
    }

    public List<ExpressProcess> expressDetail(ExpressProcess expressProcess){
        return expressProcessDao.expressDetail(expressProcess);
    }

    public void delExpPro(String[] array){
        expressProcessDao.delExpPro(array);
    };

    public void insertExpProUat(ExpressProcess expressProcess){
        expressProcessDao.insertExpProUat(expressProcess);
    }

    public List<ExpressProcess> queryByWaybillNoUat(String waybillNo){
        return expressProcessDao.queryByWaybillNoUat(waybillNo);
    };

    public void updateExpProUat(ExpressProcess expressProcess){
        expressProcessDao.updateExpProUat(expressProcess);
    }

    public List<ExpressProcess> expressDetailUat(ExpressProcess expressProcess){
        return expressProcessDao.expressDetailUat(expressProcess);
    }

    public void delExpProUat(String[] array){
        expressProcessDao.delExpProUat(array);
    };
}
