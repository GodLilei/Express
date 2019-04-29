package com.myproject.demo.dao;

import com.myproject.demo.entity.Place;
import com.myproject.demo.entity.Site;
import com.myproject.demo.entity.UserLogin;
import com.myproject.demo.entity.Warning_Index;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


public interface UserLoginDao {

    //UserLogin有返回值的方法
    public List<UserLogin> selectlogin(@Param("userlogin") UserLogin userLogin);
    public List<UserLogin> selectAllMsg();
    public List<UserLogin> selectByNameOrTemp(@Param("userlogin")UserLogin userLogin);
    public List<UserLogin> selectUname();
    public List<UserLogin> selectTemp();

    public List<UserLogin> selectLastTest(@Param("userlogin")UserLogin userLogin);

    //Site的有返回值的方法
    public List<Site> selectSiteHistory();
    public List<Site> selectSiteBookMark();

    //Place的有返回值的方法
    public List<Place> selectPlace(@Param("place")Place place);

    public List<Warning_Index> selectBillIndex(@Param("billIndex")Warning_Index warning_index);

    //UserLogin的无返回值方法
    public void insertlogin(@Param("userlogin")UserLogin userLogin);
    public void updateuser(@Param("userlogin")UserLogin userLogin);
    public void deleteData(@Param("userlogin")UserLogin userLogin);

    //Site的无返回值方法
    public void saveSite(@Param("site")Site site);
    public void deleteSiteData(@Param("site")Site site);
    public void updateSiteName(@Param("site")Site site);
    public void updateTemp(@Param("site")Site site);

    //Place的无返回值的方法

    //Warning_Index
    public void delIndex(@Param("delIndex")Warning_Index warning_index);
    public void insertIndex(@Param("insertIndex")Warning_Index warning_index);

}
