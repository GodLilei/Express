package com.myproject.demo.Dto;

import com.myproject.demo.entity.BatchExpPro;
import lombok.Data;

import java.util.List;

@Data
public class BatchExpProResponse {
    private List<BatchExpPro> batchData;
}
