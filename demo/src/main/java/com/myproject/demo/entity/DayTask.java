package com.myproject.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "daytask")
public class DayTask {
    /**
     * 每日任务标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 每日任务内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 每日任务内容
     */
    @Column(name = "type")
    private int type;

    private String typeMsg;
}
