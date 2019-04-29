package com.myproject.demo.entity;

public class Place{

    /*
     * 地点代码
     */
    private String place_code;
    /*
     * 地点名称
     */
    private String place_name;
    /*
     * 地点类型
     */
    private String place_type;

    public String getPlace_type() {
        return place_type;
    }

    public void setPlace_type(String place_type) {
        this.place_type = place_type;
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
}
