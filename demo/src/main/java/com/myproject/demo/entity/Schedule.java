/*
 * Copyright © 2015-2026 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * @since 0.0.1
 */

package com.myproject.demo.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Table(name = "schedule")
public class Schedule {

    /**
     * id
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 类型（工作1，生活2，其他3）
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 任务描述
     */
    @Column(name = "content")
    private String content;

    @Column(name = "update_time")
    private Timestamp updateTime;

    @Column(name = "create_time")
    private Timestamp createTime;

    /**
     * 完成时间
     */
    @Column(name = "finish_time")
    private Timestamp finishTime;

    /**
     * 完成状态 0为未完成,1为已完成
     */
    @Column(name = "status")
    private String status;


    private String typeMsg;

    private String statusMsg;

}
