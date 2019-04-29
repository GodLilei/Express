package com.myproject.demo.entity;

import lombok.Data;

@Data
public class ExpressProcess {

    private String waybill_no;
    private String user;
    private String create_time;
    private String customer;
    private String org_code;
    private String operate = "";
    private String other = "";
}
