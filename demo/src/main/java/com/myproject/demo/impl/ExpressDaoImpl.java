package com.myproject.demo.impl;

import com.myproject.demo.entity.Express;
import com.myproject.demo.services.ExpressServices;
import com.myproject.demo.utils.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
public class ExpressDaoImpl {

    @Resource
    private ExpressServices expressServices;
    private Express express;
    private Tool tool = new Tool();

    /**
     * 随机数
     * @return
     */
    private String randomId(){
        String num = (int)(Math.random()*10000) + "";
        return "2db7304d-d637-aaa1-bbd0-8c6fa87d" + num;
    }

    public String test(){
        return randomId();
    }

    /**
     * 日期转字符串
     */
    private String dateString(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(date);
    }

    /**
     * 公共参数：时间，单号，id，客户
     * @param oldExpress
     * @return
     */
    private Express setExpress(Express express,Express oldExpress){
        express.setWaybill_no(oldExpress.getWaybill_no());
        express.setId(randomId());//
        express.setEmp_code(oldExpress.getEmp_code());
        express.setOp_user_code(oldExpress.getOp_user_code());
        express.setOp_user_name(oldExpress.getOp_user_name());
        express.setInput_weight(oldExpress.getInput_weight());
        express.setSource_org_code(oldExpress.getSource_org_code());
        express.setTaking_org_code(oldExpress.getTaking_org_code());
        express.setDes_org_code(oldExpress.getDes_org_code());
        express.setUpload_time(tool.nowDate());
        express.setCreate_time(tool.nowDate());
        express.setModify_time(tool.nowDate());
        express.setOperate_time(tool.nowDate());
        return  express;
    }

    //揽收
    public void taking(Express oldexpress){
        log.info("----->揽收操作开始：执行机构：" + oldexpress.getTaking_org_code());
        express = new Express();
        express.setOp_code(310);
        expressServices.taking(setExpress(express,oldexpress));
    }
    //建包
    public void build(Express oldexpress){
        log.info("----->建包操作开始：执行机构："
                + oldexpress.getBuild_org_code() + ",type" + oldexpress.getBuild_org_type());
        express = new Express();
        express.setOp_code(111);
        express.setBuild_org_code(oldexpress.getBuild_org_code());
        express.setPkg_no(oldexpress.getPkg_no());
        express.setBuild_org_type(oldexpress.getBuild_org_type());
        expressServices.build(setExpress(express,oldexpress));
    }
    //下车
    public void xiache(Express oldexpress){
        log.info("----->下车操作开始：执行机构："
                + oldexpress.getBuild_org_code() + ",type" + oldexpress.getOut_org_type());
        express = new Express();
        express.setOp_code(171);//下车操作码
        express.setOut_org_code(oldexpress.getOut_org_code());
        express.setOut_org_type(oldexpress.getOut_org_type());
        express.setCar_no(oldexpress.getCar_no());
        expressServices.xiache(setExpress(express,oldexpress));
    }
    //退回
    public void out_return(Express oldexpress){
        log.info("----->退回操作开始：执行机构："
                + oldexpress.getBuild_org_code() + ",type" + oldexpress.getOut_org_type());
        express = new Express();
        express.setOp_code(171);//下车操作码
        express.setOut_org_code(oldexpress.getOut_org_code());
        express.setOut_org_type(oldexpress.getOut_org_type());
        express.setCar_no(oldexpress.getCar_no());
        express.setIo_type("02");
        expressServices.xiache(setExpress(express,oldexpress));
    }
    //派件
    public void handon(Express oldexpress){
        log.info("----->派件操作开始：执行机构：" + oldexpress.getHandon_org_code());
        express = new Express();
        express.setOp_code(710);//操作码
        express.setHandon_org_code(oldexpress.getHandon_org_code());
        expressServices.handon(setExpress(express,oldexpress));
    }
    //签收
    public void signature(Express oldexpress){
        log.info("----->签收操作开始：执行机构：" + oldexpress.getSignature_org_code());
        express = new Express();
        express.setOp_code(740);
        express.setSignature_name(oldexpress.getSignature_name());
        express.setSignature_org_code(oldexpress.getSignature_org_code());
        express.setDelivery_time(tool.nowDate());
        express.setQuery_flag(Integer.parseInt(oldexpress.getOp_user_code()));
        express.setDelivery_id(dateString().concat(oldexpress.getWaybill_no()));
        expressServices.signature(setExpress(express,oldexpress));
    }
}
