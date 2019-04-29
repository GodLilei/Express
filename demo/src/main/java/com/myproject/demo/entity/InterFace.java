package com.myproject.demo.entity;

import lombok.Data;

@Data
public class InterFace {

    /**
     * 接口名称
     */
    private String interTitle;

    /**
     * 接口备注
     */
    private String interContent;

    /**
     * 接口地址
     */
    private String interAddress;

    /**
     * 接口参数
     */
    private String interData;
}
