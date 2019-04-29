package com.myproject.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tool {

    public String nowDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
    public String dateString(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
        return simpleDateFormat.format(date);
    }
    public String userDateString(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(date);
    }
    public String twoRandom(){
        return (int)(Math.random()*100) + "";
    }
    public String fourRandom(){
        String four = (int)(Math.random()*10000) + "";
        while (four.length() != 4){
            four = (int)(Math.random()*10000) + "";
        }
        return four;
    }
}
