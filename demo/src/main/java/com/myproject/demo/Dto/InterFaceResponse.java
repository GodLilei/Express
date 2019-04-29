package com.myproject.demo.Dto;

import lombok.Data;

@Data
public class InterFaceResponse {

    /*
     * 接口标题
     */
    private String interTitle;

    /*
     * 接口内容
     */
    private String interContent;

    /*
     * 接口地址
     */
    private String interAddress;

    /*
     * 接口参数：JSON字符串
     */
    private String interData;


}
