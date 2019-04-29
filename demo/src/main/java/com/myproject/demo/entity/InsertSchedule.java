package com.myproject.demo.entity;

public class InsertSchedule {

    /*
     * id
     */
    private int id;
    /*
     * 类型（工作 1，生活 2，其他 3）
     */
    private int type;
    /*
     * 任务标题
     */
    private String title;
    /*
     * 任务描述
     */
    private String content;
    /*
     * 更新时间
     */
    private String update_time;
    /*
     * 创建时间
     */
    private String create_time;
    /*
     * 完成时间
     */
    private String finish_time;
    /*
     * 完成状态 完成 1，未完成 0
     */
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(String finish_time) {
        this.finish_time = finish_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
