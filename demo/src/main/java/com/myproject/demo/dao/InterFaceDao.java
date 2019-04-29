package com.myproject.demo.dao;

import com.myproject.demo.entity.InterFace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InterFaceDao {

    public void insertInterFace(@Param("saveInterFace")InterFace saveInterFace);
    public List<InterFace> selectInterFace();
}
