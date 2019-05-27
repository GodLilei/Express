package com.myproject.demo.controller;

import com.myproject.demo.entity.Place;
import com.myproject.demo.entity.Site;
import com.myproject.demo.entity.UserLogin;
import com.myproject.demo.entity.Warning_Index;
import com.myproject.demo.services.UserLoginServices;
import com.myproject.demo.utils.Tool;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class UserLoginController {

    @Resource
    private UserLoginServices userLoginServices;
    private UserLogin userLogin;
    private Site site;
    private Warning_Index warning_index;

    @RequestMapping(value = "/",method = {RequestMethod.GET,RequestMethod.POST})
    public String login(){
        return "/login/userlogin";
    }

//    @ResponseBody
//    @RequestMapping(value = "/testExpSit")
//    public String testExp(HttpServletResponse response,HttpServletRequest request){
//        String a = response.getHeader("Cookie");
//        Cookie cookie = new Cookie("test","test");
//        cookie.setPath("/");
//        cookie.setDomain("http://172.16.51.37:8080/testExpSit");
//        cookie.setMaxAge(99999);
//        Cookie[] cookies = request.getCookies();
//        if (null != cookies) {
//            for (Cookie cookiess : cookies) {
//                System.out.println(cookiess);
//            }
//        }
//      return "a";
//    }

    @RequestMapping(value = "/mainframe/ExpressSit",method = {RequestMethod.GET,RequestMethod.POST})
    public String loginSit(){
        return "/login/fun_six";
    }

    @RequestMapping(value = "/mainframe/ExpressUat",method = {RequestMethod.GET,RequestMethod.POST})
    public String loginUat(){
        return "/login/fun_six";
    }

    @RequestMapping(value = "/todayThings",method = {RequestMethod.GET,RequestMethod.POST})
    public String todayThings(){
        return "/today/todaythings";
    }

    @RequestMapping(value = "/gotoregister",method = {RequestMethod.GET,RequestMethod.POST})
    public String gotoregister(){
        return "/login/userregister";
    }

    @RequestMapping(value = "/lol",method = {RequestMethod.GET,RequestMethod.POST})
    public String lol(){ return "/left/lol"; }

    @RequestMapping(value = "/work",method = {RequestMethod.GET,RequestMethod.POST})
    public String work(){
        return "/left/work";
    }

    @RequestMapping(value = "/dnf",method = {RequestMethod.GET,RequestMethod.POST})
    public String dnf(){
        return "/left/dnf";
    }

    @RequestMapping(value = "/firstpage",method = {RequestMethod.GET,RequestMethod.POST})
    public String firstpage(){
        return "/page/firstpage";
    }

    @RequestMapping(value = "/noauthority",method = {RequestMethod.GET,RequestMethod.POST})
    public String noauthority(){ return "/head/no_authority"; }

    @RequestMapping(value = "/halfauthority",method = {RequestMethod.GET,RequestMethod.POST})
    public String halfauthority(){ return "/head/half_authority"; }

    @RequestMapping(value = "/checkuser",method = {RequestMethod.GET,RequestMethod.POST})
    public String checkuser(){ return "/head/check-user"; }

    @RequestMapping(value = "/fullauthority",method = {RequestMethod.GET,RequestMethod.POST})
    public String fullauthority(HttpServletRequest request){

        List<UserLogin> list = userLoginServices.selectAllMsg();
        request.setAttribute("list",list);

        return "/head/full_authority";
    }

    @RequestMapping(value = "/table",method = {RequestMethod.GET,RequestMethod.POST})
    public String table(HttpServletRequest request){

        String uname = request.getParameter("uname");
        String temp = request.getParameter("temp");

        userLogin = new UserLogin();
        userLogin.setUname(uname);
        userLogin.setTemp(temp);

        List<UserLogin> list = userLoginServices.selectByNameOrTemp(userLogin);
        userLogin = null;
        request.setAttribute("list",list);

        return "/myTemplates/table";
    }

    @RequestMapping(value = "/history",method = {RequestMethod.GET,RequestMethod.POST})
    public String history(HttpServletRequest request){

        List<Site> list = userLoginServices.selectSiteHistory();
        request.setAttribute("list",list);

        return "/head/history";
    }

    @RequestMapping(value = "/bookmark",method = {RequestMethod.GET,RequestMethod.POST})
    public String bookmark(HttpServletRequest request){

        List<Site> list = userLoginServices.selectSiteBookMark();
        request.setAttribute("list",list);

        return "/head/bookmark";
    }

    @RequestMapping(value = "/register",method = {RequestMethod.GET,RequestMethod.POST})
    public String register(HttpServletRequest request){

        String uname = request.getParameter("uname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        userLogin = new UserLogin();
        userLogin.setEmail(email);
        userLogin.setPhone(phone);
        userLogin.setPassword(password);
        userLogin.setUname(uname);

        userLoginServices.insertlogin(userLogin);
        userLogin = null;

        return "redirect:/";
    }

    @RequestMapping(value = "/mainframe",method = {RequestMethod.GET,RequestMethod.POST})
    public String denglu(HttpServletRequest request){

        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        userLogin = new UserLogin();
        userLogin.setPhone(phone);

        try{
            List<UserLogin> usermsg = userLoginServices.selectlogin(userLogin);
            if (usermsg.get(0).password.equals(password)){
                request.setAttribute("usermsg",usermsg);
                return "/page/mainframe";
            }else{
                return "redirect:/";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "/error/500";
        }
    }

    @RequestMapping(value = "/mymsg",method = {RequestMethod.GET,RequestMethod.POST})
    public String mymsg(HttpServletRequest request){

        String phone = request.getParameter("phone");
        userLogin = new UserLogin();
        userLogin.setPhone(phone);
        try{
            List<UserLogin> usermsg = userLoginServices.selectlogin(userLogin);
            request.setAttribute("usermsg",usermsg);
        }catch (Exception e){
            return "/error/500";
        }
        return "/head/mymsg";
    }

    @RequestMapping(value = "/updatemsg",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String updatemsg(HttpServletRequest request){

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String uname = request.getParameter("username");
        String temp = request.getParameter("myrole");

        if (ud(email, password, phone, uname, temp)) return "/error/500";
        return "修改成功！";
    }

    @RequestMapping(value = "/updateSiteName",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String updateSiteName(HttpServletRequest request){

        String siteName = request.getParameter("siteName");
        String siteAddress = request.getParameter("siteAddress");

        site = new Site();
        site.setSiteName(siteName);
        site.setSiteAddress(siteAddress);
        userLoginServices.updateSiteName(site);
        site = null;
        return "修改成功！";
    }

    @RequestMapping(value = "/historyToBookMark",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String historyToBookMark(HttpServletRequest request){

        String siteAddress = request.getParameter("siteAddress");
        site = new Site();
        site.setSiteAddress(siteAddress);
        userLoginServices.updateTemp(site);
        site = null;
        return "保存成功！";
    }

    @RequestMapping(value = "/selectUname",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String selectUname(HttpServletRequest request){

        String v = request.getParameter("v");
        if (v.equals("temp")){
            List<UserLogin> list = userLoginServices.selectTemp();
            JSONArray jsonArray = TempToJson(list);
            return jsonArray.toString();
        }else {
            List<UserLogin> list = userLoginServices.selectUname();
            JSONArray jsonArray = UserLoginToJson(list);
            return jsonArray.toString();
        }
    }

    @RequestMapping(value = "/selectPlace",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String selectPlace(HttpServletRequest request){
        String v = request.getParameter("v");
        Place place = new Place();
        place.setPlace_type(v);
        List<Place> list = userLoginServices.selectPlace(place);
        place = null;
        JSONArray jsonArray = PlaceToJson(list);
        return jsonArray.toString();
    }

    @RequestMapping(value = "/selectBillIndex",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String selectBillIndex(HttpServletRequest request){
        String place_code = request.getParameter("place");
        String phone = request.getParameter("phone");
        String days = request.getParameter("days");

        warning_index = new Warning_Index();
        warning_index.setPlace_code(place_code);
        warning_index.setPhone(phone);
        if (days == ""){

        }else {
            warning_index.setDays(Integer.parseInt(days));
        }

        List<Warning_Index> list = userLoginServices.selectBillIndex(warning_index);
        warning_index = null;

        JSONArray jsonArray = WarningIndexToJson(list);
        return jsonArray.toString();
    }

    @RequestMapping(value = "/excelImport",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map excelImport(MultipartFile file){
        /*
         * 实体
         * 上传状态返回值 0：成功 ，1：失败
         */
        List<Warning_Index> list = new ArrayList<>();
        warning_index = new Warning_Index();
        Map<String,String> map = new HashMap<>();
        map.put("code","0");
        map.put("msg","");
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
        /*
         * 解析输入流
         */
        Workbook wb = null;
        try {
            wb = new HSSFWorkbook(is);
        } catch (IOException e) {
            map.put("code","1");
            System.out.println("解析输入流异常");
        }
        assert wb != null;
        Sheet sheet = wb.getSheetAt(0);//第一个sheet
        /*
         * 从第二行开始遍历，第一行一般为标题
         */
        for (int r = 1; r <= sheet.getLastRowNum(); r++){
            warning_index = new Warning_Index();
            String index = "";
            Row row = sheet.getRow(r);
            try {
                String create_time = row.getCell(0).getStringCellValue();
                if (create_time.equals("")){
                    index += "时间为空 ";
                    map.put("msg",index);
                    map.put("code","1");
                }else {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = simpleDateFormat.parse(create_time);
                    warning_index.setCreate_time(date);
                }
                String phone = row.getCell(1).getStringCellValue();
                if (phone.equals("")){
                    index += "电话号码为空 ";
                    map.put("msg",index);
                    map.put("code","1");
                }else {
                    warning_index.setPhone(phone);
                }
                String days = row.getCell(2).getStringCellValue();
                if (days.equals("")){
                    index += "周期为空 ";
                    map.put("msg",index);
                    map.put("code","1");
                }else {
                    warning_index.setDays(Integer.parseInt(days));
                }
                String max_weight = row.getCell(3).getStringCellValue();
                if (max_weight.equals("")){
                    index += "最大重量为空 ";
                    map.put("msg",index);
                    map.put("code","1");
                }else {
                    warning_index.setMax_weight(Double.parseDouble(max_weight));
                }
                String max_qty = row.getCell(4).getStringCellValue();
                if (max_qty.equals("")){
                    index += "最大数量为空 ";
                    map.put("msg",index);
                    map.put("code","1");
                }else{
                    warning_index.setMax_qty(Double.parseDouble(max_qty));
                }
                String max_money = row.getCell(5).getStringCellValue();
                if (max_money.equals("")){
                    index += "最大金额为空 ";
                    map.put("msg",index);
                    map.put("code","1");
                }else{
                    warning_index.setMax_money(Double.parseDouble(max_money));
                }
                String place_name = row.getCell(6).getStringCellValue();
                if (place_name.equals("")){
                    index += "地点名称为空 ";
                    map.put("msg",index);
                    map.put("code","1");
                }else {
                    warning_index.setPlace_name(place_name);
                }
                String place_code = row.getCell(7).getStringCellValue();
                if (place_code.equals("")){
                    index += "地点代码为空 ";
                    map.put("msg",index);
                    map.put("code","1");
                }else{
                    warning_index.setPlace_code(place_code);
                }
                String type_name = row.getCell(8).getStringCellValue();
                if (type_name.equals("")){
                    index += "费用类型为空 ";
                    map.put("msg",index);
                    map.put("code","1");
                }else {
                    warning_index.setType_name(type_name);
                }
                String type_code = row.getCell(9).getStringCellValue();
                if (type_code.equals("")){
                    index += "费用类型代码为空 ";
                    map.put("msg",index);
                    map.put("code","1");
                }else {
                    warning_index.setType_code(type_code);
                }
            } catch (Exception e) {
                e.printStackTrace();
                index += "其余异常 ";
                map.put("msg",index);
                map.put("code","1");
            }
        }
        list.add(warning_index);
        if (map.get("code").equals("0")){
            userLoginServices.insertIndex(warning_index);
        }
        JSONArray jsonArray = WarningIndexToJson(list);
        map.put("data",jsonArray.toString());
        warning_index = null;
        return  map;
    }

    @RequestMapping(value = "/template_downLoad",method = {RequestMethod.GET,RequestMethod.POST})
    public void template_downLoad(HttpServletResponse response) throws IOException {
        /*
         * 创建表格
         */
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");

        /*
         * 表名
         */
        String fileName = "Warning_Index Message Templates"  + ".xls";

        int rowNum = 1;
        //headers表示excel表中第一行的表头
        String[] headers = { "Create_time", "Phone", "Days", "Max_weight", "Max_qty", "Max_money"
                , "Place_name", "Place_code", "Type_name", "Type_code"};
        //在excel表中添加表头
        HSSFRow row = sheet.createRow(0);
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        /*
         * 回收内存
         */
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    @RequestMapping(value = "/excelExport",method = {RequestMethod.GET,RequestMethod.POST})
    public void excelExport(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String days = request.getParameter("days");
        /*
         * 赋值
         */
        warning_index = new Warning_Index();
        if (days == ""){
        }else {
            warning_index.setDays(Integer.parseInt(days));
        }
        /*
         * 创建表格
         */
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
        HSSFSheet sheet1 = workbook.createSheet("信息表2");

        /*
         * 将要导出的数据存到list里面
         */
        List<Warning_Index> list = userLoginServices.selectBillIndex(warning_index);

        /*
         * 表名
         */
        String fileName = "Warning_Index Message"  + ".xls";

        int rowNum = 1;
        //headers表示excel表中第一行的表头
        String[] headers = { "Create_time", "Phone", "Days", "Max_weight", "Max_qty", "Max_money"
                , "Place_name", "Place_code", "Type_name", "Type_code"};
        //在excel表中添加表头
        HSSFRow row = sheet.createRow(0);
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for (Warning_Index warning_index : list) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(warning_index.getCreate_time());
            row1.createCell(1).setCellValue(warning_index.getPhone());
            row1.createCell(2).setCellValue(warning_index.getDays()+"");
            row1.createCell(3).setCellValue(warning_index.getMax_weight()+"");
            row1.createCell(4).setCellValue(warning_index.getMax_qty()+"");
            row1.createCell(5).setCellValue(warning_index.getMax_money()+"");
            row1.createCell(6).setCellValue(warning_index.getPlace_name());
            row1.createCell(7).setCellValue(warning_index.getPlace_code());
            row1.createCell(8).setCellValue(warning_index.getType_name());
            row1.createCell(9).setCellValue(warning_index.getType_code());
            rowNum++;
        }
        /*
         * 回收内存
         */
        warning_index = null;

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    @RequestMapping(value = "/del",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String del(HttpServletRequest request){

        String phone = request.getParameter("phone");
        userLogin = new UserLogin();
        userLogin.setPhone(phone);
        userLoginServices.deleteData(userLogin);
        return "删除成功！";
    }

    @RequestMapping(value = "/sitedel",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String sitedel(HttpServletRequest request){

        String siteAddress = request.getParameter("siteAddress");
        site = new Site();
        site.setSiteAddress(siteAddress);
        userLoginServices.deleteSiteData(site);
        return "删除成功！";
    }

    @RequestMapping(value = "/delIndex",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String delIndex(HttpServletRequest request){
        String type_code = request.getParameter("type_code");
        String place_code = request.getParameter("place_code");

        warning_index = new Warning_Index();
        warning_index.setPlace_code(place_code);
        warning_index.setType_code(type_code);

        userLoginServices.delIndex(warning_index);
        return "删除成功";
    }

    @RequestMapping(value = "/setChange",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String edit(HttpServletRequest request){

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String uname = request.getParameter("uname");
        String temp = request.getParameter("temp");

        if (ud(email, password, phone, uname, temp)) return "/error/500";

        return "编辑成功！";
    }

    @RequestMapping(value = "/saveSite",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String site(HttpServletRequest request){

        String value = request.getParameter("value");

        site = new Site();
        site.setSiteName(value);
        site.setSiteAddress(value);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String accessTime = simpleDateFormat.format(date);
        site.setAccessTime(accessTime);

        try{
            userLoginServices.saveSite(site);
        }catch (Exception e){
        }
        return "";
    }

    @RequestMapping(value = "/redisCheck",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String redisCheck(HttpServletRequest request) {

        String address = request.getParameter("address");
        String port = request.getParameter("port");
        String waybill = "00" + request.getParameter("waybill");
        String rediss = request.getParameter("redis");

        String[] keys = new String[]{"T_STL_TRANSFER_FRONT:NOTICE_0","T_STL_TRANSFER_FRONT:NOTICE_1",
                "T_STL_TRANSFER_FRONT:NOTICE_2","T_STL_TRANSFER_FRONT:NOTICE_3","T_STL_TRANSFER_FRONT:NOTICE_4",
                "T_STL_TRANSFER_FRONT:NOTICE_5","T_STL_TRANSFER_FRONT:NOTICE_6","T_STL_TRANSFER_FRONT:NOTICE_7"};
        int[] ports = new int[]{6377,7130,7131,7132};

        int len = waybill.length();
        String index = waybill.substring(len-3);
        int flag = Integer.parseInt(index);
        int place = flag%8;
        int lens = 0;
        List<String> list1;
        List<String> list2 = new ArrayList<>();

//        System.out.println("redis:" + keys[place] + " 单号：" + waybill);

        if (address.equals("") || port.equals("")){

            for (int port1 : ports) {
                Jedis redis1 = new Jedis("10.129.220.28", port1, 6000);
                lens = Math.toIntExact(redis1.llen(keys[place]));
                list1 = redis1.lrange(keys[place], 0, lens+1);
                list2.addAll(list1);
            }
            return list2.toString();
        }else{
            int port1 = Integer.parseInt(port);
            Jedis redis = new Jedis(address, port1, 6000);
            lens = Math.toIntExact(redis.llen(keys[place]));
            list1 = redis.lrange(keys[place],0,lens+1);
            return list1.toString();
        }
    }

    public boolean ud(String email, String password, String phone, String uname, String temp) {
        try{
            userLogin = new UserLogin();
            userLogin.setAll(email,password,phone,uname,temp);
            userLoginServices.updateuser(userLogin);
        }catch (Exception e){
            return true;
        }
        return false;
    }
    public static JSONArray UserLoginToJson(List<UserLogin> list){
        JSONArray json = new JSONArray();
        for(UserLogin pLog : list){
            JSONObject jo = new JSONObject();
            try {
                jo.put("uname", pLog.getUname());
                jo.put("temp", pLog.getTemp());
                jo.put("phone", pLog.getPhone());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            json.put(jo);
        }
        return json;
    }

    private static JSONArray PlaceToJson(List<Place> list){
        JSONArray json = new JSONArray();
        for(Place pLog : list){
            JSONObject jo = new JSONObject();
            try {
                jo.put("place_name", pLog.getPlace_name());
                jo.put("place_code", pLog.getPlace_code());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            json.put(jo);
        }
        return json;
    }

    private static JSONArray WarningIndexToJson(List<Warning_Index> list){
        JSONArray json = new JSONArray();
        for(Warning_Index pLog : list){
            JSONObject jo = new JSONObject();
            try {
                jo.put("type_code", pLog.getType_code());
                jo.put("type_name", pLog.getType_name());
                jo.put("place_code", pLog.getPlace_code());
                jo.put("place_name", pLog.getPlace_name());
                jo.put("max_money", pLog.getMax_money());
                jo.put("max_qty", pLog.getMax_qty());
                jo.put("max_weight", pLog.getMax_weight());
                jo.put("days", pLog.getDays());
                jo.put("phone", pLog.getPhone());
                jo.put("create_time", pLog.getCreate_time());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            json.put(jo);
        }
        return json;
    }

    public static JSONArray TempToJson(List<UserLogin> list){
        JSONArray json = new JSONArray();
        for(UserLogin pLog : list){
            JSONObject jo = new JSONObject();
            try {
                jo.put("temp", pLog.getTemp());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            json.put(jo);
        }
        return json;
    }

}
