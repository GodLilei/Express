package com.myproject.demo.controller;

import com.myproject.demo.Dto.*;
import com.myproject.demo.entity.ExpressProcess;
import com.myproject.demo.impl.ExpressProcessImpl;
import com.myproject.demo.utils.Tool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
public class ExpressProcessController {

    @Resource
    private ExpressProcessImpl expressProcessImpl;

    @ResponseBody
    @RequestMapping(value = "/insertEPSit",method = {RequestMethod.GET,RequestMethod.POST})
    public void signature(@RequestBody ExpressProcess ep){
        expressProcessImpl.queryByWaybillNo(ep);
    }

    @ResponseBody
    @RequestMapping(value = "/insertEPUat",method = {RequestMethod.GET,RequestMethod.POST})
    public void signatureUat(@RequestBody ExpressProcess ep){
        expressProcessImpl.queryByWaybillNoUat(ep);
    }

    @ResponseBody
    @RequestMapping(value = "/batchAddSit",method = {RequestMethod.GET,RequestMethod.POST})
    public Response batchAdd(@RequestBody BatchExpProResponse batchExpProResponse){
        return expressProcessImpl.batchAdd(batchExpProResponse);
    }
    @ResponseBody
    @RequestMapping(value = "/batchAddUat",method = {RequestMethod.GET,RequestMethod.POST})
    public Response batchAddUat(@RequestBody BatchExpProResponse batchExpProResponse){
        return expressProcessImpl.batchAddUat(batchExpProResponse);
    }

    @RequestMapping(value = "/addExpProDemoSit",method = {RequestMethod.GET,RequestMethod.POST})
    public String addExpProDemo(){
        return "/demo/expProDemoSit";
    }
    @RequestMapping(value = "/addExpProDemoUat",method = {RequestMethod.GET,RequestMethod.POST})
    public String addExpProDemoUat(){
        return "/demo/expProDemoSit";
    }

    @RequestMapping(value = "/expImportSit",method = {RequestMethod.GET,RequestMethod.POST})
    public String expImport(){
        return "/demo/expImportDemoSit";
    }
    @RequestMapping(value = "/expImportUat",method = {RequestMethod.GET,RequestMethod.POST})
    public String expImportUat(){
        return "/demo/expImportDemoSit";
    }

    @RequestMapping(value = "/template_downLoad_exp",method = {RequestMethod.GET,RequestMethod.POST})
    public void template_downLoad(HttpServletResponse response) throws IOException {
        Tool tools = new Tool();
        tools.template_downLoad(response);
    }

    @RequestMapping(value = "/expDataImportSit",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map expDataImport(MultipartFile file) throws IOException{
        return expressProcessImpl.expDataImport(file);
    }
    @RequestMapping(value = "/expDataImportUat",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map expDataImportUat(MultipartFile file) throws IOException{
        return expressProcessImpl.expDataImportUat(file);
    }

    @ResponseBody
    @RequestMapping(value = "/delExpProSit",method = {RequestMethod.GET,RequestMethod.POST})
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
    @RequestMapping(value = "/delExpProUat",method = {RequestMethod.GET,RequestMethod.POST})
    public Response delExpProUat(@RequestBody StringArrayResponse stringArrayResponse){
        Response response = new Response();
        try{
            String[] array = stringArrayResponse.getArrays();
            expressProcessImpl.delExpProUat(array);
            response.setCode("0");
            response.setMessage("删除成功");
        }catch (Exception e){
            response.setCode("-1");
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/queryExpressSit",method = {RequestMethod.GET,RequestMethod.POST})
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
    @ResponseBody
    @RequestMapping(value = "/queryExpressUat",method = {RequestMethod.GET,RequestMethod.POST})
    public ExpressProcessResponse queryExpressUat(@RequestBody ExpressProcess ep){
        ExpressProcessResponse expressProcessResponse = new ExpressProcessResponse();
        try{
            expressProcessResponse.setEp(expressProcessImpl.expressDetailUat(ep));
        }catch (Exception e){
            expressProcessResponse.setMessage(e.getMessage());
            expressProcessResponse.setCode("-1");
        }
        return expressProcessResponse;
    }
}
