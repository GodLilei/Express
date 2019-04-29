package com.myproject.demo.controller;

import com.alibaba.fastjson.JSON;
import com.myproject.demo.Dto.BaseResponse;
import com.myproject.demo.Dto.DayTaskResponse;
import com.myproject.demo.entity.DayTask;
import com.myproject.demo.entity.InsertSchedule;
import com.myproject.demo.enums.Type;
import com.myproject.demo.services.InsertScheduleServices;
import com.myproject.demo.utils.InterTestHttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class InsertScheduleController {

    @Resource
    private InsertScheduleServices insertScheduleServices;

    private InsertSchedule insertSchedule;
    private DayTask dayTask;

    @RequestMapping(value = "/schedule",method = {RequestMethod.GET,RequestMethod.POST})
    public String schedule(){

        return "/today/schedule";
    }

    @RequestMapping(value = "/scheduleMake",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String scheduleMake(HttpServletRequest request){

        String time = request.getParameter("time");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String type = request.getParameter("type");
        String status = "0";

        insertSchedule = new InsertSchedule();
        insertSchedule.setType(Integer.parseInt(type));
        insertSchedule.setTitle(title);
        insertSchedule.setContent(content);
        insertSchedule.setCreate_time(correntTime());
        insertSchedule.setFinish_time(time);
        insertSchedule.setStatus(status);

        try {
            insertScheduleServices.insertSchedule(insertSchedule);

        } catch (Exception e) {
            e.printStackTrace();
        }
        insertSchedule = null;
        return "添加成功";
    }

    @RequestMapping(value = "/uploadDayTaskSelect",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String uploadDayTaskSelect(@RequestBody DayTaskResponse flag){

        List<DayTask> list = flag.getDayTaskData();

        for (DayTask aList : list) {
            for (Type type : Type.values()) {
                if (aList.getTypeMsg() == type.getMsg()) {
                    aList.setType(type.getCode());
                }
            }
        }

        for (DayTask list1:list){
            insertSchedule = new InsertSchedule();
            insertSchedule.setCreate_time(correntTime());
            insertSchedule.setFinish_time(correntTime());
            insertSchedule.setTitle(list1.getTitle());
            insertSchedule.setContent(list1.getContent());
            insertSchedule.setType(list1.getType());
            insertSchedule.setStatus("0");
            insertScheduleServices.insertSchedule(insertSchedule);
            insertSchedule = null;
        }

        return "保存成功";
    }

    @RequestMapping(value = "/chooseTask",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public BaseResponse chooseTask(HttpServletRequest request){

        BaseResponse baseResponse = new BaseResponse();
        String title = request.getParameter("title");

        dayTask = new DayTask();
        dayTask.setTitle(title);

        List<DayTask> list = insertScheduleServices.selectDayTask(dayTask);
        for (DayTask aList : list) {
            for (Type type : Type.values()) {
                if (aList.getType() == type.getCode()) {
                    aList.setTypeMsg(type.getMsg());
                }
            }
        }

        baseResponse.setData(JSON.parseArray(JSON.toJSONString(list)));
        baseResponse.setMsg("查找成功");
        baseResponse.setCode("200");
        return  baseResponse;
    }

    private String correntTime(){

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return simpleDateFormat.format(date);
    }
}
