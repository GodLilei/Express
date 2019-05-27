package com.myproject.demo.impl;

import com.myproject.demo.Dto.BatchExpProResponse;
import com.myproject.demo.Dto.Response;
import com.myproject.demo.entity.BatchExpPro;
import com.myproject.demo.entity.Express;
import com.myproject.demo.entity.ExpressProcess;
import com.myproject.demo.services.ExpressProcessServices;
import com.myproject.demo.utils.HttpClientResponse;
import com.myproject.demo.utils.HttpServer;
import com.myproject.demo.utils.InterTestHttpServer;
import com.myproject.demo.utils.Tool;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ExpressProcessImpl {

    @Resource
    private ExpressProcessServices expressProcessServices;
    @Resource
    private ExpressDaoImpl expressDao;
    @Resource
    private ExpressProcessImpl expressProcessImpl;
    @Resource
    private ExpressUatDaoImpl expressUatDao;

    private Tool tools = new Tool();

    private void insertExpPro(ExpressProcess expressProcess){
        expressProcessServices.insertExpPro(expressProcess);
    }
    private void insertExpProUat(ExpressProcess expressProcess){
        expressProcessServices.insertExpProUat(expressProcess);
    }

    private void updateExpPro(ExpressProcess expressProcess){
        expressProcessServices.updateExpPro(expressProcess);
    }
    private void updateExpProUat(ExpressProcess expressProcess){
        expressProcessServices.updateExpProUat(expressProcess);
    }

    public List<ExpressProcess> expressDetail(ExpressProcess expressProcess){
        return expressProcessServices.expressDetail(expressProcess);
    }
    public List<ExpressProcess> expressDetailUat(ExpressProcess expressProcess){
        return expressProcessServices.expressDetailUat(expressProcess);
    }

    public void queryByWaybillNo(ExpressProcess expressProcess){
        String waybillNo = expressProcess.getWaybill_no();
        List<ExpressProcess> list = expressProcessServices.queryByWaybillNo(waybillNo);
        if (list.size() == 0){
            log.info("----->未检测到单号存在，执行insert操作" + expressProcess.getWaybill_no());
            expressProcess.setCreate_time(tools.nowDate());
            expressProcess.setUser(expressProcess.getUser().concat(tools.userDateString()));
            insertExpPro(expressProcess);
        }else {
            log.info("----->更新操作：" + expressProcess.getOperate());
            String operate = list.get(0).getOperate();
            expressProcess.setOperate(operate.concat(expressProcess.getOperate()));
            updateExpPro(expressProcess);
        }
    }
    public void queryByWaybillNoUat(ExpressProcess expressProcess){
        String waybillNo = expressProcess.getWaybill_no();
        List<ExpressProcess> list = expressProcessServices.queryByWaybillNoUat(waybillNo);
        if (list.size() == 0){
            log.info("----->未检测到单号存在，执行insert操作" + expressProcess.getWaybill_no());
            expressProcess.setCreate_time(tools.nowDate());
            expressProcess.setUser(expressProcess.getUser().concat(tools.userDateString()));
            insertExpProUat(expressProcess);
        }else {
            log.info("----->更新操作：" + expressProcess.getOperate());
            String operate = list.get(0).getOperate();
            expressProcess.setOperate(operate.concat(expressProcess.getOperate()));
            updateExpProUat(expressProcess);
        }
    }


    public void delExpPro(String[] array){
        expressProcessServices.delExpPro(array);
    }
    public void delExpProUat(String[] array){
        expressProcessServices.delExpProUat(array);
    }

    public Response batchAdd(BatchExpProResponse batchExpProResponse){
        List<BatchExpPro> list = batchExpProResponse.getBatchData();
        Response response = new Response();

        InterTestHttpServer interTestHttpServer = null;
        HttpServer httpServer = null;
        Express taking = null;
        ExpressProcess ep = null;

        StringBuilder ll = new StringBuilder();

        for (BatchExpPro aList : list) {
            interTestHttpServer = new InterTestHttpServer();
            httpServer = new HttpServer();
            taking = new Express();
            ep = new ExpressProcess();

            String customer = aList.getCustomer();
            String orgCode = aList.getOrgCode();
            String expPro = aList.getExpPro();
            String desOrgCode = aList.getDesOrgCode();
            Tool tools = new Tool();
            HttpClientResponse httpClient = new HttpClientResponse();
            Map<String,Object> map = tools.JsonStringToMap("{\n" +
                    "\"ChannelCode\":\"TAOBAO\",\n" +
                    "\"OrgCode\":\"" + orgCode + "\"\n" +
                    "}");

            try{
                /*获取单号*/
                String wayBillNo = httpClient.requestPost("http://jingang.msns.cn/yttssit/GetWaybillNo",map);
                ll.append(wayBillNo).append("|");
                /*下单*/
                String msg = httpServer.weChatSend(customer,orgCode,wayBillNo,taking.getSeller(),desOrgCode);

                /*设置单号信息，是否存在单号，如果不存在，则新增到数据库*/
                ep.setWaybill_no(wayBillNo);
                ep.setUser("system");
                queryByWaybillNo(ep);

                taking.setWaybill_no(wayBillNo);
                taking.setEmp_code(customer);
                taking.setOp_user_code("00003520");
                taking.setOp_user_name("自动操作");

                tools.exp(expPro,expressDao,expressProcessImpl,taking,ep);

            }catch (Exception e){
                response.setCode("-1");
                response.setMessage(e.getMessage());
            }

        }
        response.setMessage(ll.toString());
        return response;
    }
    public Response batchAddUat(BatchExpProResponse batchExpProResponse){
        List<BatchExpPro> list = batchExpProResponse.getBatchData();
        Response response = new Response();

        InterTestHttpServer interTestHttpServer = null;
        HttpServer httpServer = null;
        Express taking = null;
        ExpressProcess ep = null;

        StringBuilder ll = new StringBuilder();

        for (BatchExpPro aList : list) {
            interTestHttpServer = new InterTestHttpServer();
            httpServer = new HttpServer();
            taking = new Express();
            ep = new ExpressProcess();

            String customer = aList.getCustomer();
            String orgCode = aList.getOrgCode();
            String expPro = aList.getExpPro();
            String desOrgCode = aList.getDesOrgCode();
            Tool tools = new Tool();
            String[] key = {"ChannelCode","OrgCode","waybillNo"};
            String[] value = {"TAOBAO",orgCode,"81".concat(tools.dateString()).concat(tools.fourRandom())};

            try{
                /*获取单号*/
                String wayBillNo = interTestHttpServer.HttpGetofJSONString("http://jingang.msns" +
                                ".cn/yttsuat/GetWaybillNo18",key,
                        value);
                ll.append(wayBillNo).append("|");
                /*下单*/
                String msg = httpServer.weChatSendUat(customer,orgCode,wayBillNo,taking.getSeller(),desOrgCode);

                /*设置单号信息，是否存在单号，如果不存在，则新增到数据库*/
                ep.setWaybill_no(wayBillNo);
                ep.setUser("system");
                queryByWaybillNoUat(ep);

                taking.setWaybill_no(wayBillNo);
                taking.setEmp_code(customer);
                taking.setOp_user_code("00003520");
                taking.setOp_user_name("自动操作");

                tools.expUat(expPro,expressUatDao,expressProcessImpl,taking,ep);

            }catch (Exception e){
                response.setCode("-1");
                response.setMessage(e.getMessage());
            }

        }
        response.setMessage(ll.toString());
        return response;
    }

    public Map expDataImport(MultipartFile file) throws IOException,NullPointerException {
        InterTestHttpServer interTestHttpServer = null;
        HttpServer httpServer = null;
        Express taking = null;
        ExpressProcess ep = null;

        Map<String,String> map = new HashMap<>();
        map.put("code","0");
        map.put("msg","success");
        /*
         * file转输入流
         * 出现异常更改状态为 1
         */
        InputStream is = null;
        try {
            is = file.getInputStream();
        } catch (IOException e) {
            map.put("code","1");
            System.out.println("file转输入流异常");
        }
        HSSFWorkbook workbook = null;
        try{
            if (is != null)
                workbook = new HSSFWorkbook(is);
        }catch (IOException e){
            map.put("code","1");
            System.out.println("解析SIT输入流异常");
        }

        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);
            StringBuilder index = new StringBuilder();
            String orgCode = null;
            String customer = null;
            String seller = null;
            String desOrgCode = null;
            String epp = null;
            for (int r = 1; r <= sheet.getLastRowNum(); r++){
                Row row = sheet.getRow(r);
                try{
                    orgCode = row.getCell(0).getStringCellValue();
                }catch (Exception e){
                    orgCode = "210077";
                    log.info("SIT无物料发放网点，默认为210077");
                    index.append("无物料发放网点，默认为210077");
                }
                try{
                    customer = row.getCell(1).getStringCellValue();
                }catch (Exception e){
                    customer = "K21002107";
                    log.info("SIT无物料绑定客户，默认为K21002107");
                    index.append("无物料绑定客户，默认为K21002107");
                }
                try{
                    seller = row.getCell(2).getStringCellValue();
                }catch (Exception e){
                    seller = "2575775285";
                    log.info("SIT无绑定商家信息，默认为123456789");
                    index.append("无绑定商家信息，默认为123456789");
                }
                try{
                    desOrgCode = row.getCell(3).getStringCellValue();
                }catch (Exception e){
                    desOrgCode = "210045";
                    log.info("SIT无下单目的网点，默认210045");
                    index.append("无下单目的网点，默认210045");
                }
                try{
                    epp = row.getCell(4).getStringCellValue();
                }catch (Exception e){
                    log.info("导入模板中无走件信息");
                    map.put("code","1");
                    index.append("无SIT走件信息");
                    map.put("msg",index.toString());
                    return map;
                }
                Tool tools = new Tool();
                String[] key = {"ChannelCode","OrgCode","waybillNo"};
                String[] value = {"TAOBAO",orgCode,"82".concat(tools.dateString()).concat(tools.fourRandom())};
                interTestHttpServer = new InterTestHttpServer();
                httpServer = new HttpServer();
                taking = new Express();
                ep = new ExpressProcess();

                try{
                    /*获取单号，83*/
                    String wayBillNo = interTestHttpServer.HttpGetofJSONString("http://jingang.msns" +
                                    ".cn/yttssit/GetWaybillNo18",key,
                            value);
                    /*下单*/
                    String msg = httpServer.weChatSend(customer,orgCode,wayBillNo,seller,desOrgCode);

                    /*设置单号信息，是否存在单号，如果不存在，则新增到数据库*/
                    ep.setWaybill_no(wayBillNo);
                    ep.setUser("import");
                    queryByWaybillNo(ep);

                    taking.setWaybill_no(wayBillNo);
                    taking.setEmp_code(customer);
                    taking.setOp_user_code("00003520");
                    taking.setOp_user_name("导入");

                    tools.exp(epp,expressDao,expressProcessImpl,taking,ep);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else {
            map.put("code","1");
            System.out.println("取sheet异常");
        }
        System.out.println(map.toString());
        return  map;
    }

    public Map expDataImportUat(MultipartFile file) throws IOException {
        InterTestHttpServer interTestHttpServer = null;
        HttpServer httpServer = null;
        Express taking = null;
        ExpressProcess ep = null;

        Map<String,String> map = new HashMap<>();
        map.put("code","0");
        map.put("msg","success");
        /*
         * file转输入流
         * 出现异常更改状态为 1
         */
        InputStream is = null;
        try {
            is = file.getInputStream();
        } catch (IOException e) {
            map.put("code","1");
            System.out.println("file转输入流异常");
        }
        HSSFWorkbook workbook = null;
        try{
            if (is != null)
                workbook = new HSSFWorkbook(is);
        }catch (IOException e){
            map.put("code","1");
            System.out.println("解析UAT输入流异常");
        }

        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(0);
            StringBuilder index = new StringBuilder();
            String orgCode = null;
            String customer = null;
            String seller = null;
            String desOrgCode = null;
            String epp = null;
            for (int r = 1; r <= sheet.getLastRowNum(); r++){
                Row row = sheet.getRow(r);
                try{
                    orgCode = row.getCell(0).getStringCellValue();
                }catch (Exception e){
                    orgCode = "210077";
                    log.info("UAT无物料发放网点，默认为210077");
                    index.append("无物料发放网点，默认为210077");
                }
                try{
                    customer = row.getCell(1).getStringCellValue();
                }catch (Exception e){
                    customer = "K21002107";
                    log.info("UAT无物料绑定客户，默认为K21002107");
                    index.append("无物料绑定客户，默认为K21002107");
                }
                try{
                    seller = row.getCell(2).getStringCellValue();
                }catch (Exception e){
                    seller = "2575775285";
                    log.info("UAT无绑定商家信息，默认为123456789");
                    index.append("无绑定商家信息，默认为123456789");
                }
                try{
                    desOrgCode = row.getCell(3).getStringCellValue();
                }catch (Exception e){
                    desOrgCode = "210045";
                    log.info("UAT无下单目的网点，默认210045");
                    index.append("无下单目的网点，默认210045");
                }
                try{
                    epp = row.getCell(4).getStringCellValue();
                }catch (Exception e){
                    map.put("code","1");
                    index.append("无UAT走件信息");
                    map.put("msg",index.toString());
                    System.out.println("这里报错了！！");
                    return map;
                }

                Tool tools = new Tool();
                String[] key = {"ChannelCode","OrgCode","waybillNo"};
                String[] value = {"TAOBAO",orgCode,"82".concat(tools.dateString()).concat(tools.fourRandom())};
                interTestHttpServer = new InterTestHttpServer();
                httpServer = new HttpServer();
                taking = new Express();
                ep = new ExpressProcess();

                try{
                    /*获取单号，83*/
                    String wayBillNo = interTestHttpServer.HttpGetofJSONString("http://jingang.msns" +
                                    ".cn/yttsuat/GetWaybillNo18",key,
                            value);
                    /*下单*/
                    String msg = httpServer.weChatSendUat(customer,orgCode,wayBillNo,seller,desOrgCode);

                    /*设置单号信息，是否存在单号，如果不存在，则新增到数据库*/
                    ep.setWaybill_no(wayBillNo);
                    ep.setUser("import");
                    queryByWaybillNoUat(ep);

                    taking.setWaybill_no(wayBillNo);
                    taking.setEmp_code(customer);
                    taking.setOp_user_code("00003520");
                    taking.setOp_user_name("UAT导入");

                    tools.expUat(epp,expressUatDao,expressProcessImpl,taking,ep);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else {
            map.put("code","1");
            System.out.println("取sheet异常");
        }
        System.out.println(map.toString());
        return  map;
    }
}
