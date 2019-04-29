package com.myproject.demo.Dto;

import com.myproject.demo.entity.ExpressProcess;
import lombok.Data;

import java.util.List;

public class ExpressProcessResponse extends Response{

    /**返回主体*/
    private List<ExpressProcess> ep;

    public List<ExpressProcess> getEp() {
        return ep;
    }

    public void setEp(List<ExpressProcess> ep) {
        this.ep = ep;
    }
}
