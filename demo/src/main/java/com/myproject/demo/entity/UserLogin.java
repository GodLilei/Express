package com.myproject.demo.entity;

public class UserLogin {

    /*
     * 电话
     */
    public String phone;
    /*
     * 用户名
     */
    public String uname;
    /*
     * 密码
     */
    public String password;
    /*
     * 邮箱
     */
    public String email;
    /*
     * 权限 0，1，2
     */
    public String temp;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTemp() {
        return temp;
    }

    public void setAll(String email,String password,String phone,String uname,String temp){
        this.email=email;
        this.password=password;
        this.phone = phone;
        this.uname = uname;
        this.temp = temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
