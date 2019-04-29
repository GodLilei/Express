package com.myproject.demo.Dto;

import com.myproject.demo.entity.DayTask;
import lombok.Data;

import java.util.List;

@Data
public class DayTaskResponse {

    private List<DayTask> dayTaskData;
}
