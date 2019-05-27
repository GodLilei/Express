package com.myproject.demo.impl;

import com.alibaba.fastjson.JSON;
import com.myproject.demo.Dto.BaseResponse;
import com.myproject.demo.entity.Express;
import com.myproject.demo.services.ExpressServices;
import com.myproject.demo.utils.HttpClientResponse;
import com.myproject.demo.utils.HttpServer;
import com.myproject.demo.utils.InterTestHttpServer;
import com.myproject.demo.utils.Tool;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Cookie;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

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
    Express setExpress(Express express, Express oldExpress){
        express.setWaybill_no(oldExpress.getWaybill_no());
        express.setId(randomId());//
        express.setEmp_code(oldExpress.getEmp_code());
        express.setOp_user_code(oldExpress.getOp_user_code());
        express.setOp_user_name(oldExpress.getOp_user_name());
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
        express.setInput_weight(oldexpress.getInput_weight());
        expressServices.taking(setExpress(express,oldexpress));
    }
    //建包
    public void build(Express oldexpress){
        log.info("----->建包操作开始：执行机构："
                + oldexpress.getBuild_org_code() + ",type：" + oldexpress.getBuild_org_type());
        express = new Express();
        express.setOp_code(111);
        express.setBuild_org_code(oldexpress.getBuild_org_code());
        express.setPkg_no(oldexpress.getPkg_no());
        express.setBuild_org_type(oldexpress.getBuild_org_type());
        expressServices.build(setExpress(express,oldexpress));
    }

    //拆包
    public void unpack(Express oldexpress){
        log.info("----->拆包操作开始：执行机构："
                + oldexpress.getBuild_org_code() + ",type：" + oldexpress.getBuild_org_type());
        express = new Express();
        express.setOp_code(181);
        express.setInput_weight(oldexpress.getInput_weight());
        express.setFee_weight(oldexpress.getInput_weight());
        express.setBuild_org_code(oldexpress.getBuild_org_code());
        express.setPkg_no(oldexpress.getPkg_no());
        express.setBuild_org_type(oldexpress.getBuild_org_type());
        expressServices.build(setExpress(express,oldexpress));
    }

    //下车
    public void xiache(Express oldexpress){
        log.info("----->下车操作开始：执行机构："
                + oldexpress.getOut_org_code() + ",type：" + oldexpress.getOut_org_type());
        express = new Express();
        express.setOp_code(171);//下车操作码
        express.setInput_weight(oldexpress.getOut_weight());
        express.setOut_org_code(oldexpress.getOut_org_code());
        express.setOut_org_type(oldexpress.getOut_org_type());
        express.setCar_no(oldexpress.getCar_no());
        expressServices.xiache(setExpress(express,oldexpress));
    }

    //计泡
    public void bubble(Express oldexpress){
        log.info("----->计泡操作开始：执行机构："
                + oldexpress.getJipao_org_code() + ",type：" + oldexpress.getJipao_org_type());
        express = new Express();
        express.setOp_code(179);//下车操作码
        express.setInput_weight(0.00);
        express.setOut_org_code(oldexpress.getOut_org_code());
        express.setOut_org_type(oldexpress.getOut_org_type());
        express.setJipao_org_code(oldexpress.getJipao_org_code());
        express.setJipao_org_type(oldexpress.getJipao_org_type());
        express.setPkg_height(oldexpress.getPkg_height());
        express.setPkg_length(oldexpress.getPkg_length());
        express.setPkg_width(oldexpress.getPkg_width());
        express.setVolume_weight(oldexpress.getVolume_weight());
        express.setCar_no(oldexpress.getCar_no());
        expressServices.xiache(setExpress(express,oldexpress));
    }

    //退回
    public void out_return(Express oldexpress){
        log.info("----->退回操作开始：执行机构："
                + oldexpress.getOut_org_code() + ",type：" + oldexpress.getOut_org_type());
        express = new Express();
        express.setOp_code(171);//下车操作码
        express.setInput_weight(oldexpress.getOut_weight());
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
    //检查是否签收
    public int checkHandon(String waybill){
        return expressServices.checkHandon(waybill);
    }

    public BaseResponse waybillGet(Express taking){
        BaseResponse baseResponse = new BaseResponse();
        HttpClientResponse httpClient = new HttpClientResponse();
        Map<String,Object> map = tool.JsonStringToMap("{\n" +
                "\"ChannelCode\":\"TAOBAO\",\n" +
                "\"OrgCode\":\"" + taking.getSource_org_code() + "\"\n" +
                "}");

        try{
            String waybillNo = httpClient.requestPost("http://jingang.msns.cn/yttssit/GetWaybillNo",map);
            baseResponse.setCode("0");
//            baseResponse.setData(JSON.parseArray("[{\"wbn\":\"" + msg.substring(msg.length()-18) + "\"}]"));
            baseResponse.setData(JSON.parseArray("[{\"wbn\":\"" + waybillNo + "\"}]"));
        }catch (Exception e){
            baseResponse.setCode("-1");
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    public BaseResponse xiadan(Express taking){
        BaseResponse baseResponse = new BaseResponse();
        HttpServer httpServer = new HttpServer();
        try{
            String msg = httpServer.weChatSend(taking.getEmp_code(),taking.getSource_org_code(),taking.getWaybill_no
                    (),taking.getSeller(),taking.getDes_org_code());
            if (msg.length() <= 20){
                baseResponse.setCode("1");
                baseResponse.setMsg("下单接口异常，暂时无法下单！");
            }else {
                baseResponse.setCode("0");
                baseResponse.setMsg("下单成功！网点：" + taking.getSource_org_code() + " 客户：" + taking.getEmp_code() +
                        "单号：" + taking.getWaybill_no());
            }
        }catch (Exception e){
            baseResponse.setCode("-1");
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }
}
