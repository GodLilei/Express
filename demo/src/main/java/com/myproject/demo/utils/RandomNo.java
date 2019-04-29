package com.myproject.demo.utils;

public class RandomNo {
    /**
     * 四位随机数
     */
    public String fourRandom(){
        return twoRandom().concat(twoRandom());
    }
    /**
     * 两位随机数
     */
    public String twoRandom(){
        return (int)(Math.random()*100) + "";
    }
    /**
     * 六位随机数
     */
    public String sixRandom(){
        return twoRandom().concat(fourRandom());
    }
}
