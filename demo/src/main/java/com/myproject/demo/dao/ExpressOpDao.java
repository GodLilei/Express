package com.myproject.demo.dao;

import com.myproject.demo.entity.Express;
import org.apache.ibatis.annotations.Param;

public interface ExpressOpDao {
    /**
     * 揽收
     * @param express
     */
    public void taking(@Param("taking")Express express);
    /**
     * 建包
     * @param express
     */
    public void build(@Param("build")Express express);
    /**
     * 下车
     * @param express
     */
    public void xiache(@Param("xiache")Express express);
    /**
     * 派件
     * @param express
     */
    public void handon(@Param("handon")Express express);
    /**
     * 签收
     * @param express
     */
    public void signature(@Param("signature")Express express);
}
