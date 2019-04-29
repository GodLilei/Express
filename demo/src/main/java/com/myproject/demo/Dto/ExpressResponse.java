package com.myproject.demo.Dto;

import com.myproject.demo.entity.Express;
import lombok.Data;

import java.util.List;

public class ExpressResponse extends Response{

//    private String code = "0";
    private List<Express> expresses;
//    private String message = null;

    public List<Express> getExpresses() {
        return expresses;
    }

    public void setExpresses(List<Express> expresses) {
        this.expresses = expresses;
    }
}
