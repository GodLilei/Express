/*
 * Copyright © 2015-2026 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * @since 0.0.1
 */

package com.myproject.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class HttpServer {

    CloseableHttpClient httpClient = HttpClients.createDefault();

    String entityStr = null;

    CloseableHttpResponse response = null;

    public String weChatSend(String CustomerCode, String SendOrgCode, String WaybillNo,String seller, String
            desOrgCode){

        log.info("----->执行下单接口：");
        try {
            URIBuilder uriBuilder = new URIBuilder("http://jingang.msns.cn/yttssit/CreateOrderS");
            List<NameValuePair> params = new LinkedList<>();
            params.add(new BasicNameValuePair("ChannelCode","TAOBAO"));
            params.add(new BasicNameValuePair("ChannelKey","jNpKcyXrHfNJ"));
            params.add(new BasicNameValuePair("SellerCode",seller));
            params.add(new BasicNameValuePair("NetworkType","A"));
            params.add(new BasicNameValuePair("OrderType","online"));
            params.add(new BasicNameValuePair("IsAli","N"));
            params.add(new BasicNameValuePair("CustomerCode",CustomerCode));
            params.add(new BasicNameValuePair("ServiceType","0"));
            params.add(new BasicNameValuePair("IdCard","342401199412064079"));
            params.add(new BasicNameValuePair("SendName","%E5%BC%A0%E4%B8%89"));
            params.add(new BasicNameValuePair("SendOrgCode",SendOrgCode));
            params.add(new BasicNameValuePair("SendPhoneNo","15921108467"));
            params.add(new BasicNameValuePair("ReceiveName","%E6%9D%8E%E5%9B%9B"));
            params.add(new BasicNameValuePair("ReceiveOrgCode",desOrgCode));
            params.add(new BasicNameValuePair("ReceivePhoneNo","15921108453"));
            params.add(new BasicNameValuePair("AddService","1"));
            params.add(new BasicNameValuePair("OrdPrefix","TB"));
            params.add(new BasicNameValuePair("WaybillNo",WaybillNo));
            params.add(new BasicNameValuePair("Cod","0"));
            params.add(new BasicNameValuePair("Pay","0"));
            params.add(new BasicNameValuePair("Keep","0"));
            uriBuilder.addParameters(params);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            // 使用Apache提供的工具类进行转换成字符串
            entityStr = EntityUtils.toString(entity, "UTF-8");
            log.info("----->下单接口访问成功，返回值：" + entityStr);
        } catch (URISyntaxException e) {
            System.out.println("Url解析出错");
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            System.out.println("http协议出错");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO异常");
            e.printStackTrace();
        }finally {
            if (null!=response){
                try {
                    response.close();
                } catch (IOException e) {
                    System.out.println("释放链接出错");
                    e.printStackTrace();
                }
            }
        }

        return entityStr;
    }


    public String weChatSendUat(String CustomerCode, String SendOrgCode, String WaybillNo,String seller, String
            desOrgCode){

        log.info("----->UAT执行下单接口：");
        try {
            URIBuilder uriBuilder = new URIBuilder("http://jingang.msns.cn/yttsuat/CreateOrderS");
            List<NameValuePair> params = new LinkedList<>();
            params.add(new BasicNameValuePair("ChannelCode","TAOBAO"));
            params.add(new BasicNameValuePair("ChannelKey","jNpKcyXrHfNJ"));
            params.add(new BasicNameValuePair("SellerCode",seller));
            params.add(new BasicNameValuePair("NetworkType","A"));
            params.add(new BasicNameValuePair("OrderType","online"));
            params.add(new BasicNameValuePair("IsAli","N"));
            params.add(new BasicNameValuePair("CustomerCode",CustomerCode));
            params.add(new BasicNameValuePair("ServiceType","0"));
            params.add(new BasicNameValuePair("IdCard","342401199412064079"));
            params.add(new BasicNameValuePair("SendName","张三"));
            params.add(new BasicNameValuePair("SendOrgCode",SendOrgCode));
            params.add(new BasicNameValuePair("SendPhoneNo","15921108467"));
            params.add(new BasicNameValuePair("ReceiveName","李四"));
            params.add(new BasicNameValuePair("ReceiveOrgCode",desOrgCode));
            params.add(new BasicNameValuePair("ReceivePhoneNo","15921108453"));
            params.add(new BasicNameValuePair("AddService","1"));
            params.add(new BasicNameValuePair("OrdPrefix","TB"));
            params.add(new BasicNameValuePair("WaybillNo",WaybillNo));
            params.add(new BasicNameValuePair("Cod","0"));
            params.add(new BasicNameValuePair("Pay","0"));
            params.add(new BasicNameValuePair("Keep","0"));
            uriBuilder.addParameters(params);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            // 使用Apache提供的工具类进行转换成字符串
            entityStr = EntityUtils.toString(entity, "UTF-8");
            log.info("----->下单接口访问成功，返回值：" + entityStr);
        } catch (URISyntaxException e) {
            System.out.println("Url解析出错");
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            System.out.println("http协议出错");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO异常");
            e.printStackTrace();
        }finally {
            if (null!=response){
                try {
                    response.close();
                } catch (IOException e) {
                    System.out.println("释放链接出错");
                    e.printStackTrace();
                }
            }
        }

        return entityStr;
    }
}
