package com.myproject.demo.services;

import com.myproject.demo.dao.UserLoginDao;
import com.myproject.demo.entity.Place;
import com.myproject.demo.entity.Site;
import com.myproject.demo.entity.UserLogin;
import com.myproject.demo.entity.Warning_Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserLoginServices {

    @Resource
    private UserLoginDao userLoginDao;

    public List<UserLogin> selectlogin(UserLogin userLogin){
        return userLoginDao.selectlogin(userLogin);
    }
    public List<UserLogin> selectAllMsg(){ return userLoginDao.selectAllMsg(); }
    public List<UserLogin> selectByNameOrTemp(UserLogin userLogin) { return userLoginDao.selectByNameOrTemp(userLogin); }
    public List<UserLogin> selectUname() { return userLoginDao.selectUname(); }
    public List<UserLogin> selectTemp() { return userLoginDao.selectTemp(); }

    public List<UserLogin> selectLastTest(UserLogin userLogin){ return userLoginDao.selectLastTest(userLogin); }

    public List<Site> selectSiteHistory(){ return userLoginDao.selectSiteHistory(); }
    public List<Site> selectSiteBookMark(){ return userLoginDao.selectSiteBookMark(); }

    public List<Place> selectPlace(Place place){ return userLoginDao.selectPlace(place); }

    public List<Warning_Index> selectBillIndex(Warning_Index warning_index){ return userLoginDao.selectBillIndex(warning_index); }

    public void insertlogin(UserLogin userLogin){ userLoginDao.insertlogin(userLogin); }
    public void updateuser(UserLogin userLogin){ userLoginDao.updateuser(userLogin); }
    public void deleteData(UserLogin userLogin){ userLoginDao.deleteData(userLogin); }

    public void saveSite(Site site){ userLoginDao.saveSite(site); }
    public void deleteSiteData(Site site){ userLoginDao.deleteSiteData(site); }
    public void updateSiteName(Site site){ userLoginDao.updateSiteName(site); }
    public void updateTemp(Site site){ userLoginDao.updateTemp(site); }

    public void delIndex(Warning_Index warning_index){ userLoginDao.delIndex(warning_index); }
    public void insertIndex(Warning_Index warning_index){ userLoginDao.insertIndex(warning_index); }

}
