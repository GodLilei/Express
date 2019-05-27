package com.myproject.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myproject.demo.entity.Express;
import com.myproject.demo.entity.ExpressProcess;
import com.myproject.demo.impl.ExpressDaoImpl;
import com.myproject.demo.impl.ExpressProcessImpl;
import com.myproject.demo.impl.ExpressUatDaoImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Tool {

    public String nowDate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
    public String dateString(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
        return simpleDateFormat.format(date);
    }
    public String userDateString(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(date);
    }
    public String fourRandom(){
        String four = (int)(Math.random()*10000) + "";
        while (four.length() != 4){
            four = (int)(Math.random()*10000) + "";
        }
        return four;
    }
    public void exp(String expPro,ExpressDaoImpl expressDao,ExpressProcessImpl expressProcessImpl,Express taking,
                    ExpressProcess ep) throws InterruptedException{
        log.info("----->自动走件操作开始:----------------------------------->");
        log.info("----->" + expPro);
        String[] step = expPro.split("\\|");
        for (String s:step){
            switch (s.substring(0, 2)) {
                case "揽收": {
                    String code = s.substring(2,8);
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
                    Thread.sleep(1000);
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
                    Thread.sleep(1000);
                    break;
                }
                case "下车": {
                    String code = s.substring(2,8);
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
                    Thread.sleep(1000);
                    break;
                }
                case "退回": {
                    String code = s.substring(2,8);
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
                    Thread.sleep(1000);
                    break;
                }
                case "派件": {
                    String code = s.substring(2);
                    taking.setHandon_org_code(code);
                    expressDao.handon(taking);
                    ep.setOperate("|派件" + code);
                    expressProcessImpl.queryByWaybillNo(ep);
                    Thread.sleep(1000);
                    break;
                }
                case "签收": {
                    int flag = 0;
                    String code = s.substring(2);
                    taking.setSignature_org_code(code);
                    taking.setSignature_name("签收自动");
                    log.info("-----》》查询派件开始(5s/次)：" + taking.getWaybill_no());
                    while (expressDao.checkHandon(taking.getWaybill_no()) == 0 && flag <= 10){
                        Thread.sleep(5000);
                        flag++;
                    }
                    log.info("-----》》查询派件结束：查询次数：" + flag);
                    expressDao.signature(taking);
                    ep.setOperate("|签收" + code);
                    expressProcessImpl.queryByWaybillNo(ep);
                    break;
                }
            }
        }
    }
    //Uat自动操作
    public void expUat(String expPro, ExpressUatDaoImpl expressUatDao, ExpressProcessImpl expressProcessImpl, Express
            taking, ExpressProcess ep) throws InterruptedException{
        log.info("----->自动走件操作开始:----------------------------------->");
        log.info("----->" + expPro);
        String[] step = expPro.split("\\|");
        for (String s:step){
            switch (s.substring(0, 2)) {
                case "揽收": {
                    String code = s.substring(2,8);
                    double weight = 10.00;
                    if (s.length() > 9){
                        weight = Double.parseDouble(s.substring(9,s.length()-1));
                    }
                    taking.setInput_weight(weight);
                    taking.setSource_org_code(code);
                    taking.setTaking_org_code(code);
                    taking.setDes_org_code(code);
                    expressUatDao.taking(taking);
                    ep.setOperate("|揽收" + code + "[" + weight + "]");
                    expressProcessImpl.queryByWaybillNoUat(ep);
                    Thread.sleep(1000);
                    break;
                }
                case "建包": {
                    String code = s.substring(2);
                    taking.setBuild_org_code(code);
                    taking.setBuild_org_type("TRANSFER_CENTER");
                    taking.setPkg_no("WB1230396056");
                    expressUatDao.build(taking);
                    ep.setOperate("|建包" + code);
                    expressProcessImpl.queryByWaybillNoUat(ep);
                    Thread.sleep(1000);
                    break;
                }
                case "下车": {
                    String code = s.substring(2,8);
                    double weight = 10.00;
                    if (s.length() > 9){
                        weight = Double.parseDouble(s.substring(9,s.length()-1));
                    }
                    taking.setInput_weight(weight);
                    taking.setOut_org_code(code);
                    taking.setOut_org_type("TRANSFER_CENTER");
                    taking.setCar_no("CQ12344321");
                    expressUatDao.xiache(taking);
                    ep.setOperate("|下车" + code + "[" + weight + "]");
                    expressProcessImpl.queryByWaybillNoUat(ep);
                    Thread.sleep(1000);
                    break;
                }
                case "退回": {
                    String code = s.substring(2,8);
                    double weight = 10.00;
                    if (s.length() > 9){
                        weight = Double.parseDouble(s.substring(9,s.length()-1));
                    }
                    taking.setInput_weight(weight);
                    taking.setOut_org_code(code);
                    taking.setOut_org_type("TRANSFER_CENTER");
                    taking.setCar_no("CQ12344321");
                    expressUatDao.out_return(taking);
                    ep.setOperate("|退回" + code + "[" + weight + "]");
                    expressProcessImpl.queryByWaybillNoUat(ep);
                    Thread.sleep(1000);
                    break;
                }
                case "派件": {
                    String code = s.substring(2);
                    taking.setHandon_org_code(code);
                    expressUatDao.handon(taking);
                    ep.setOperate("|派件" + code);
                    expressProcessImpl.queryByWaybillNoUat(ep);
                    Thread.sleep(1000);
                    break;
                }
                case "签收": {
                    int flag = 0;
                    String code = s.substring(2);
                    taking.setSignature_org_code(code);
                    taking.setSignature_name("签收自动");
                    log.info("-----》》查询派件开始(5s/次)：" + taking.getWaybill_no());
                    while (expressUatDao.checkHandon(taking.getWaybill_no()) == 0 && flag <= 10){
                        Thread.sleep(5000);
                        flag++;
                    }
                    log.info("-----》》查询派件结束：查询次数：" + flag);
                    expressUatDao.signature(taking);
                    ep.setOperate("|签收" + code);
                    expressProcessImpl.queryByWaybillNoUat(ep);
                    break;
                }
            }
        }
    }
    public void template_downLoad(HttpServletResponse response) throws IOException {
        /*
         * 创建表格
         * */
        log.info("模板下载中------------>>>");
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("走件信息录入");

        HSSFCellStyle cellStyle=workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFFont fontStyle=workbook.createFont();
        fontStyle.setFontName("宋体");
        fontStyle.setFontHeightInPoints((short)16);
        cellStyle.setFont(fontStyle);

        HSSFDataFormat format = workbook.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("@"));
        /*
         * 设置列宽
         */
        sheet.setColumnWidth(0,256*18);
        sheet.setColumnWidth(1,256*18);
        sheet.setColumnWidth(2,256*18);
        sheet.setColumnWidth(3,256*18);
        sheet.setColumnWidth(4,256*80);

        /*
         * 设置行高
         */
        sheet.setDefaultRowHeight((short)(1.5*256));

        /*
         * 表名
         */
        String fileName = "Express"  + ".xls";

        //headers表示excel表中第一行的表头
        String[] headers = { "orgCode", "customer", "seller", "desOrgCode", "express"};
        //在excel表中添加表头
        HSSFRow row = sheet.createRow(0);

        row.setRowStyle(cellStyle);
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            cell.setCellStyle(cellStyle);
        }

        for(int i=1;i<2;i++){
            HSSFRow row1 = sheet.createRow(i);
            row1.setRowStyle(cellStyle);
        }
        /*
         * 回收内存
         */
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    /**
     *
     * @param jsonString json字符串转MAP
     * @return MAP
     */
    public Map<String,Object> JsonStringToMap(String jsonString){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        JSONArray jsonArray = JSON.parseArray("["+jsonString+"]");
        for (Object aJsonArray : jsonArray) {
            JSONObject obj = (JSONObject) JSONObject.toJSON(aJsonArray);//传过来的值的size为1，去掉外层循环
            //int i = 0;
            for (Map.Entry<String, Object> entry : obj.entrySet()) {
                resultMap.put(entry.getKey(),entry.getValue());
            }
        }
        return resultMap;
    }

    /**
     *
     * @param jsonString json字符串
     * @return JSON类
     */
    public JSONObject JsonStringToJson(String jsonString){
        return JSONObject.parseObject(jsonString);
    }
}
