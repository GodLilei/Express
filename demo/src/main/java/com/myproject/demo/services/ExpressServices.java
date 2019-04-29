package com.myproject.demo.services;

import com.myproject.demo.dao.ExpressOpDao;
import com.myproject.demo.entity.Express;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ExpressServices {

    @Resource
    private ExpressOpDao expressOpDao;

    /**
     * 揽收
     * @param express
     */
    public void taking(Express express){
        expressOpDao.taking(express);
    }
    /**建包*/
    public void build(Express express){
        expressOpDao.build(express);
    }
    public void xiache(Express express){
        expressOpDao.xiache(express);
    }
    public void handon(Express express){
        expressOpDao.handon(express);
    }
    public void signature(Express express){
        expressOpDao.signature(express);
    }
}
