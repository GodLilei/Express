package com.myproject.demo.controller;

import com.alibaba.fastjson.JSON;
import com.myproject.demo.Dto.BaseResponse;
import com.myproject.demo.Dto.ExpressResponse;
import com.myproject.demo.entity.Express;
import com.myproject.demo.impl.ExpressDaoImpl;
import com.myproject.demo.utils.HttpServer;
import com.myproject.demo.utils.InterTestHttpServer;
import com.myproject.demo.utils.RandomNo;
import com.myproject.demo.utils.Tool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class ExpressController {

    @Resource
    private ExpressDaoImpl expressDao;

    @ResponseBody
    @RequestMapping(value = "/taking",method = {RequestMethod.GET,RequestMethod.POST})
    public ExpressResponse taking(@RequestBody Express taking){
        ExpressResponse expressResponse = new ExpressResponse();
        try{
            expressDao.taking(taking);
            expressResponse.setMessage("揽收操作成功");
        }catch (Exception e){
            expressResponse.setCode("-1");
            expressResponse.setMessage(e.getMessage());
        }
        return expressResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/build",method = {RequestMethod.GET,RequestMethod.POST})
    public ExpressResponse build(@RequestBody Express taking){
        ExpressResponse expressResponse = new ExpressResponse();
        try{
            expressDao.build(taking);
            expressResponse.setMessage("建包操作成功");
        }catch (Exception e){
            expressResponse.setCode("-1");
            expressResponse.setMessage(e.getMessage());
        }
        return expressResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/waybillGet",method = {RequestMethod.GET,RequestMethod.POST})
    public BaseResponse waybillGet(@RequestBody Express taking){

        BaseResponse baseResponse = new BaseResponse();
        InterTestHttpServer interTestHttpServer = new InterTestHttpServer();
        HttpServer httpServer = new HttpServer();
        Tool tools = new Tool();
        String[] key = {"ChannelCode","OrgCode","waybillNo"};
        String[] value = {"TAOBAO",taking.getSource_org_code(),"88".concat(tools.dateString()).concat(tools.fourRandom())};
        try{
            String wayBillNo = interTestHttpServer.HttpGetofJSONString("http://jingang.msns.cn/ytts/GetWaybillNo18",key,
                    value);

//            String msg = httpServer.weChatSend(taking.getEmp_code(),taking.getSource_org_code(),wayBillNo);
//            System.out.println(msg + "-----" + msg.length() + "--->" + msg.substring(msg.length()-18));
            baseResponse.setCode("0");
//            baseResponse.setData(JSON.parseArray("[{\"wbn\":\"" + msg.substring(msg.length()-18) + "\"}]"));
            baseResponse.setData(JSON.parseArray("[{\"wbn\":\"" + wayBillNo + "\"}]"));
//            baseResponse.setData(JSON.parseArray("[{\"wbn\":" + "111111111111111111" + "}]"));
        }catch (Exception e){
            baseResponse.setCode("-1");
            baseResponse.setMsg(e.getMessage());
        }

        return baseResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/xiadan",method = {RequestMethod.GET,RequestMethod.POST})
    public BaseResponse xiadan(@RequestBody Express taking){
        BaseResponse baseResponse = new BaseResponse();
        HttpServer httpServer = new HttpServer();
        try{
            String msg = httpServer.weChatSend(taking.getEmp_code(),taking.getSource_org_code(),taking.getWaybill_no
                    (),taking.getSeller());
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

    @ResponseBody
    @RequestMapping(value = "/out",method = {RequestMethod.GET,RequestMethod.POST})
    public ExpressResponse out(@RequestBody Express taking){
        ExpressResponse expressResponse = new ExpressResponse();
        try{
            expressDao.xiache(taking);
            expressResponse.setMessage("下车操作成功");
        }catch (Exception e){
            expressResponse.setCode("-1");
            expressResponse.setMessage(e.getMessage());
        }
        return expressResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/outReturn",method = {RequestMethod.GET,RequestMethod.POST})
    public ExpressResponse outReturn(@RequestBody Express taking){
        ExpressResponse expressResponse = new ExpressResponse();
        try{
            expressDao.out_return(taking);
            expressResponse.setMessage("退回操作成功");
        }catch (Exception e){
            expressResponse.setCode("-1");
            expressResponse.setMessage(e.getMessage());
        }
        return expressResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/handon",method = {RequestMethod.GET,RequestMethod.POST})
    public ExpressResponse handon(@RequestBody Express taking){
        ExpressResponse expressResponse = new ExpressResponse();
        try{
            expressDao.handon(taking);
            expressResponse.setMessage("派件操作成功");
        }catch (Exception e){
            expressResponse.setCode("-1");
            expressResponse.setMessage(e.getMessage());
        }
        return expressResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/signature",method = {RequestMethod.GET,RequestMethod.POST})
    public ExpressResponse signature(@RequestBody Express taking){
        ExpressResponse expressResponse = new ExpressResponse();
        try{
            expressDao.signature(taking);
            expressResponse.setMessage("签收操作成功");
        }catch (Exception e){
            expressResponse.setCode("-1");
            expressResponse.setMessage(e.getMessage());
        }
        return expressResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/testExp")
    public String testExp(){
//        HttpServer httpServer = new HttpServer();
//        return httpServer.weChatSend("1","1","1");
//        return JSON.parseArray(JSON.toJSONString("运单号：123123123"));
        Tool tool = new Tool();
        String test = tool.dateString();
        return test;
    }
}
