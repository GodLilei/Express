package com.myproject.demo.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Warning_Index {

    /*
     * 费用类型
     */
    private String type_code;
    /*
     * 费用类型
     */
    private String type_name;
    /*
     * 地点代码
     */
    private String place_code;
    /*
     * 地点名称
     */
    private String place_name;
    /*
     * 最大金额指标
     */
    private double max_money;
    /*
     * 最大数量指标
     */
    private double max_qty;
    /*
     * 最大重量指标
     */
    private double max_weight;
    /*
     * 周期
     */
    private int days;
    /*
     * 电话号码
     */
    public String phone;
    /*
     * 创建时间
     */
    private String create_time;

    public String getType_code() {
        return type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getPlace_code() {
        return place_code;
    }

    public void setPlace_code(String place_code) {
        this.place_code = place_code;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public double getMax_money() {
        return max_money;
    }

    public void setMax_money(double max_money) {
        this.max_money = max_money;
    }

    public double getMax_qty() {
        return max_qty;
    }

    public void setMax_qty(double max_qty) {
        this.max_qty = max_qty;
    }

    public double getMax_weight() {
        return max_weight;
    }

    public void setMax_weight(double max_weight) {
        this.max_weight = max_weight;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.format(create_time);
        this.create_time = simpleDateFormat.format(create_time);
    }

    public void setAll(String type_code,String type_name,String place_code,String place_name,double max_money,double max_qty,double max_weight,int days,String phone,String create_time){
        this.type_code = type_code;
        this.type_name = type_name;
        this.place_code = place_code;
        this.place_name = place_name;
        this.max_money = max_money;
        this.max_qty = max_qty;
        this.max_weight = max_weight;
        this.days = days;
        this.phone = phone;
        this.create_time =create_time;
    }
}
