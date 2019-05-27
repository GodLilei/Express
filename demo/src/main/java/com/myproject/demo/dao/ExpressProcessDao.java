package com.myproject.demo.dao;

import com.myproject.demo.entity.ExpressProcess;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExpressProcessDao {

    /**数据插入数据库*/
    public void insertExpPro(@Param("msg")ExpressProcess expressProcess);
    public void insertExpProUat(@Param("msg")ExpressProcess expressProcess);

    /**根据单号查询数据*/
    public List<ExpressProcess> queryByWaybillNo(String waybillNo);
    public List<ExpressProcess> queryByWaybillNoUat(String waybillNo);

    /**更新本地走件流程*/
    public void updateExpPro(@Param("msg")ExpressProcess expressProcess);
    public void updateExpProUat(@Param("msg")ExpressProcess expressProcess);

    /**查询本地走件流程*/
    public List<ExpressProcess> expressDetail(@Param("detail")ExpressProcess expressProcess);
    public List<ExpressProcess> expressDetailUat(@Param("detail")ExpressProcess expressProcess);

    /**批量删除*/
    public void delExpPro(@Param("array")String[] array);
    public void delExpProUat(@Param("array")String[] array);
}
