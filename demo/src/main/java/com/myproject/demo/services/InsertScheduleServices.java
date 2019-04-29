package com.myproject.demo.services;

import com.myproject.demo.dao.InsertScheduleDao;
import com.myproject.demo.dao.UserLoginDao;
import com.myproject.demo.entity.DayTask;
import com.myproject.demo.entity.InsertSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class InsertScheduleServices {

    @Resource
    private InsertScheduleDao insertScheduleDao;

    public void insertSchedule(InsertSchedule insertSchedule){
        insertScheduleDao.insertSchedule(insertSchedule);
    }
    public List<DayTask> selectDayTask(DayTask dayTask){
        return insertScheduleDao.selectDayTask(dayTask);
    }
}
