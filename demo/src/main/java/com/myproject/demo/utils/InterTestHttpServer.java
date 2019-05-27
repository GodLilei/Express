package com.myproject.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class InterTestHttpServer {

    private CloseableHttpClient httpClient = HttpClients.createDefault();
    private String entityStr = null;
    private CloseableHttpResponse response = null;

    /**
     * get请求
     * @param url 接口接收的参数地址
     * @param key json的key
     * @param value json的value
     */
    public String HttpGetofJSONString(String url,String[] key,String[] value) {

        log.info("-------------------分隔符-------------------------");
        log.info("----->Get请求开始：url:" + url);
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            List<NameValuePair> params = new LinkedList<>();
            if (key != null){
                for (int i = 0; i < key.length; i++){
                    params.add(new BasicNameValuePair(key[i],value[i]));
                }
            }
            uriBuilder.addParameters(params);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("Cookie","TOKEN=MEE1RTQyQjI4NjRCQTMzQUIxQUU4N0UzMEE4NEYzMjhDRTZFNDQwQzRCNUU2MTUyRThCQjU4MjJBQ0U2N0EzMkFEOTYwOTFDNjVENjJFMzZENkQ3OUU5NEMxMDFBQjRDQzhCMDcxMjMwMkQwRUNCMzhBM0JBMEIzMEUzODVFQTZFNDkzREQ3M0FFMkE2MDQ4OTQ4NDk0ODQxQjE3QkMyQQ==");
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            // 使用Apache提供的工具类进行转换成字符串
            entityStr = EntityUtils.toString(entity, "UTF-8");
            log.info("----->Get请求成功，返回：" + entityStr);
//            httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  entityStr;
    }

    /**
     * POST请求
     * @param url 接口接收的参数地址
     * @param jsonParams 接口参数,content文本
     */
    public String HttpPostofJSONString(String url,String jsonParams){

        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(180 * 1000).setConnectionRequestTimeout(180 * 1000)
                .setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();

        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type","application/json");  //设置格式
        httpPost.setHeader("Cookie","TOKEN=MzgzNTRBNzZBQUMzMTI1NDE4MUQ4ODg1MTAzNjU1MTNEQTJENjZFQTU2OTYxRDA1MTE0NkRERjg4RDY2NEI2MUQ2OTNERkJFRTMyMUI1ODBFQUQ4NDQxN0IxMDdDNDkzODg2Q0M4NkNGN0E3ODc2MTlGNDdBMjc1RTg1NjAwNEZFNDkzREQ3M0FFMkE2MDQ4OTQ4NDk0ODQxQjE3QkMyQQ==");

        //请求开始-------------------------
        log.info("----->Post请求开始，url:" + url);
        try {
            httpPost.setEntity(new StringEntity(jsonParams,ContentType.create("application/json", "utf-8")));//设置参数
//            System.out.println("request parameters" + EntityUtils.toString(httpPost.getEntity()));//检验参数是否设置成功
            response = httpClient.execute(httpPost);//请求

//            System.out.println(" code:"+response.getStatusLine().getStatusCode());//返回状态码
//            System.out.println("doPostForInfobipUnsub response"+response.getStatusLine().toString());//返回状态信息

            HttpEntity entity = response.getEntity();
            // 使用Apache提供的工具类进行转换成字符串
            entityStr = EntityUtils.toString(entity, "UTF-8");
//            System.out.println(entityStr);//返回值

//            return String.valueOf(response.getStatusLine().getStatusCode());
            log.info("----->Post请求成功，返回：" + entityStr);
            return entityStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "post failure :caused by-->" + e.getMessage().toString();
        }finally {
            try{
                httpPost.releaseConnection();
            }catch (Exception e){
                e.printStackTrace();
            }
            //            if(null != httpClient){
//                try {
//                    httpClient.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }
}
