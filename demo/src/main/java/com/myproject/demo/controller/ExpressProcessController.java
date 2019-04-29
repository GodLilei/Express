package com.myproject.demo.controller;

import com.alibaba.fastjson.JSON;
import com.myproject.demo.Dto.*;
import com.myproject.demo.entity.BatchExpPro;
import com.myproject.demo.entity.Express;
import com.myproject.demo.entity.ExpressProcess;
import com.myproject.demo.impl.ExpressDaoImpl;
import com.myproject.demo.impl.ExpressProcessImpl;
import com.myproject.demo.utils.HttpServer;
import com.myproject.demo.utils.InterTestHttpServer;
import com.myproject.demo.utils.Tool;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ExpressProcessController {

    @Resource
    private ExpressProcessImpl expressProcessImpl;
    @Resource
    private ExpressDaoImpl expressDao;

    @ResponseBody
    @RequestMapping(value = "/insertEP",method = {RequestMethod.GET,RequestMethod.POST})
    public void signature(@RequestBody ExpressProcess ep){
        expressProcessImpl.queryByWaybillNo(ep);
    }

    @ResponseBody
    @RequestMapping(value = "/batchAdd",method = {RequestMethod.GET,RequestMethod.POST})
    public Response batchAdd(@RequestBody BatchExpProResponse batchExpProResponse){
        List<BatchExpPro> list = batchExpProResponse.getBatchData();
        Response response = new Response();

        InterTestHttpServer interTestHttpServer = null;
        HttpServer httpServer = null;
        Express taking = null;
        ExpressProcess ep = null;

        String ll = "";

        for (BatchExpPro aList : list) {
            interTestHttpServer = new InterTestHttpServer();
            httpServer = new HttpServer();
            taking = new Express();
            ep = new ExpressProcess();

            String customer = aList.getCustomer();
            String orgCode = aList.getOrgCode();
            String expPro = aList.getExpPro();
            Tool tools = new Tool();
            String[] key = {"ChannelCode","OrgCode","waybillNo"};
            String[] value = {"TAOBAO",orgCode,"88".concat(tools.dateString()).concat(tools.fourRandom())};

            try{
                /*获取单号*/
                String wayBillNo = interTestHttpServer.HttpGetofJSONString("http://jingang.msns.cn/ytts/GetWaybillNo18",key,
                        value);
                /*下单*/
                String msg = httpServer.weChatSend(customer,orgCode,wayBillNo,taking.getSeller());

                /*设置单号信息，是否存在单号，如果不存在，则新增到数据库*/
                ep.setWaybill_no(wayBillNo);
                ep.setUser("system");
                expressProcessImpl.queryByWaybillNo(ep);

                taking.setWaybill_no(wayBillNo);
                taking.setEmp_code(customer);
                taking.setOp_user_code("00003520");
                taking.setOp_user_name("自动操作");
                taking.setInput_weight(10.0);

                /*解析走件*/
                String[] step = expPro.split("\\|");
                for (String s:step){
                    switch (s.substring(0, 2)) {
                        case "揽收": {
                            String code = s.substring(2);
                            double weight = 10.00;
                            if (s.length() > 9){
                                weight = Double.parseDouble(s.substring(9,s.length()-1));
                            }
                            taking.setInput_weight(weight);
                            taking.setSource_org_code(code);
                            taking.setTaking_org_code(code);
                            taking.setDes_org_code(code);
                            expressDao.taking(taking);
                            ep.setOperate("|揽收" + code + "[" + weight + "]");
                            expressProcessImpl.queryByWaybillNo(ep);
                            break;
                        }
                        case "建包": {
                            String code = s.substring(2);
                            taking.setBuild_org_code(code);
                            taking.setBuild_org_type("TRANSFER_CENTER");
                            taking.setPkg_no("WB1230396056");
                            expressDao.build(taking);
                            ep.setOperate("|建包" + code);
                            expressProcessImpl.queryByWaybillNo(ep);
                            break;
                        }
                        case "下车": {
                            String code = s.substring(2);
                            double weight = 10.00;
                            if (s.length() > 9){
                                weight = Double.parseDouble(s.substring(9,s.length()-1));
                            }
                            taking.setInput_weight(weight);
                            taking.setOut_org_code(code);
                            taking.setOut_org_type("TRANSFER_CENTER");
                            taking.setCar_no("CQ12344321");
                            expressDao.xiache(taking);
                            ep.setOperate("|下车" + code + "[" + weight + "]");
                            expressProcessImpl.queryByWaybillNo(ep);
                            break;
                        }
                        case "退回": {
                            String code = s.substring(2);
                            double weight = 10.00;
                            if (s.length() > 9){
                                weight = Double.parseDouble(s.substring(9,s.length()-1));
                            }
                            taking.setInput_weight(weight);
                            taking.setOut_org_code(code);
                            taking.setOut_org_type("TRANSFER_CENTER");
                            taking.setCar_no("CQ12344321");
                            expressDao.out_return(taking);
                            ep.setOperate("|退回" + code + "[" + weight + "]");
                            expressProcessImpl.queryByWaybillNo(ep);
                            break;
                        }
                        case "派件": {
                            String code = s.substring(2);
                            taking.setHandon_org_code(code);
                            expressDao.handon(taking);
                            ep.setOperate("|派件" + code);
                            expressProcessImpl.queryByWaybillNo(ep);
                            break;
                        }
                        case "签收": {
                            String code = s.substring(2);
                            taking.setSignature_org_code(code);
                            taking.setSignature_name("签收自动");
                            expressDao.signature(taking);
                            ep.setOperate("|签收" + code);
                            expressProcessImpl.queryByWaybillNo(ep);
                            break;
                        }
                    }
                }
                ll.concat(wayBillNo);
            }catch (Exception e){
                response.setCode("-1");
                response.setMessage(e.getMessage());
            }

        }
        response.setMessage(ll);
        return response;
    }

    @RequestMapping(value = "/addExpProDemo",method = {RequestMethod.GET,RequestMethod.POST})
    public String addExpProDemo(){
        return "/demo/expProDemo";
    }

    @ResponseBody
    @RequestMapping(value = "/delExpPro",method = {RequestMethod.GET,RequestMethod.POST})
    public Response delExpPro(@RequestBody StringArrayResponse stringArrayResponse){
        Response response = new Response();
        try{
            String[] array = stringArrayResponse.getArrays();
            expressProcessImpl.delExpPro(array);
            response.setCode("0");
            response.setMessage("删除成功");
        }catch (Exception e){
            response.setCode("-1");
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/queryExpress",method = {RequestMethod.GET,RequestMethod.POST})
    public ExpressProcessResponse queryExpress(@RequestBody ExpressProcess ep){
        ExpressProcessResponse expressProcessResponse = new ExpressProcessResponse();
        try{
            expressProcessResponse.setEp(expressProcessImpl.expressDetail(ep));
        }catch (Exception e){
            expressProcessResponse.setMessage(e.getMessage());
            expressProcessResponse.setCode("-1");
        }
        return expressProcessResponse;
    }
}
