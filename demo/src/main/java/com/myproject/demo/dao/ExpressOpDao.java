package com.myproject.demo.dao;

import com.myproject.demo.entity.Express;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExpressOpDao {
    /**
     * 揽收
     * @param express 1
     */
    public void taking(@Param("taking")Express express);
    /**
     * 建包
     * @param express 1
     */
    public void build(@Param("build")Express express);
    /**
     * 下车
     * @param express 1
     */
    public void xiache(@Param("xiache")Express express);
    /**
     * 派件
     * @param express 1
     */
    public void handon(@Param("handon")Express express);
    /**
     * 签收
     * @param express 1
     */
    public void signature(@Param("signature")Express express);

    /**
     *
     * @param waybill 1
     * @return 1
     */
    public Integer checkHandon(String waybill);
}
