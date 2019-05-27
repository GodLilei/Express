package com.myproject.demo.controller;

import com.myproject.demo.utils.Tool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
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
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author panhailing
 * 2014-01-23
 */
@Slf4j
public class Encoder {
		
	/**
	 * md5+base64 加密
	 * @param input 
	 * @param parterid 
	 * @return
	 * @throws Exception
	 */
	public static String MD5Base64(String input,String parterid) throws Exception{
		return new String(Base64.encodeBase64(DigestUtils.md5((input+parterid).getBytes("UTF-8"))));
	}
	/**
	 * 安全验证
	 * @param data
	 * @param digestData
	 * @param parterid
	 * @return
	 * @throws Exception
	 */
	public static boolean validateSecurity(String data, String digestData,String parterid){
		try {
			String newDigestData = MD5Base64(data,parterid);
			if(!newDigestData.equals(digestData))
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void main(String args []) throws Exception {

		for (int i=0;i<10;i++){
			Thread.sleep(1000);
			System.out.println(i);
		}

//		String a = "揽收210077[]";
//		System.out.println(a.length());


//	    String data = "1488507815505'0001','0002'5";
//		String data = "05";
//		String data = "0'0002'0";

//		String data = "长沙上海市";
//
//	    String ENCRYPT_KEY = "qzd478ivjlgxz";
//	    String newDigestData = MD5Base64(data,ENCRYPT_KEY);
//	    System.out.println(newDigestData);
//		String a = "系统不存在该始发网点，请维护";
//		System.out.println(new Tool().fourRandom());
//
//		String sss = "揽收210077|揽收210077|揽收210077|揽收210077|揽收210077";
//		try{
//            String[] step = sss.split("\\|");
//            for (String s:step){
//                if (s.substring(0,2).equals("揽收")){
//                    String code = s.substring(2);
//                    System.out.println(code);
//                }
//            }
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//		double temp = 1;
//		int vi = 1;
//		double vj = 1.0;
//		int count = 1;
//
//		for (int i = 1; i <= 14; i++) {
//			if (i % 2 == 0) {
//				for (double j = i + 1.0; j < i * 2.0 && j <= 20; j += 2.0) {
//					System.out.println(i + " " + j + " " + i/j + "  " + count++);
//					if (Math.abs(i/j - 0.618) < temp) {
//						temp = Math.abs(i / j - 0.618);
//						vi = i;
//						vj = j;
//					}
//				}
//			} else {
//				for (double j = i + 1.0; j < i * 2.0 && j <= 20; j++) {
//					System.out.println(i + " " + j + " " + i/j + "  " + count++);
//					if (Math.abs(i/j - 0.618) < temp) {
//						temp = Math.abs(i / j - 0.618);
//						vi = i;
//						vj = j;
//					}
//				}
//			}
//		}
//		System.out.println(vi + " " + vj + " " + vi/vj + " " + temp);

//        System.out.println(weChatSend("K21002107","210077","812321236547611321","2575775285"));
	}



//    private static String weChatSend(String CustomerCode, String SendOrgCode, String WaybillNo,String seller){
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//
//        String entityStr = null;
//
//        CloseableHttpResponse response = null;
//
//        log.info("----->执行下单接口：");
//        try {
//            URIBuilder uriBuilder = new URIBuilder("http://jingang.msns.cn/ytts/CreateOrderS");
//            List<NameValuePair> params = new LinkedList<>();
//            params.add(new BasicNameValuePair("ChannelCode","TAOBAO"));
//            params.add(new BasicNameValuePair("ChannelKey","jNpKcyXrHfNJ"));
//            params.add(new BasicNameValuePair("SellerCode",seller));
//            params.add(new BasicNameValuePair("NetworkType","A"));
//            params.add(new BasicNameValuePair("OrderType","online"));
//            params.add(new BasicNameValuePair("IsAli","N"));
//            params.add(new BasicNameValuePair("CustomerCode",CustomerCode));
//            params.add(new BasicNameValuePair("ServiceType","0"));
//            params.add(new BasicNameValuePair("IdCard","342401199412064079"));
//            params.add(new BasicNameValuePair("SendName","张三"));
//            params.add(new BasicNameValuePair("SendOrgCode",SendOrgCode));
//            params.add(new BasicNameValuePair("SendPhoneNo","15921108467"));
//            params.add(new BasicNameValuePair("ReceiveName","李四"));
//            params.add(new BasicNameValuePair("ReceiveOrgCode","210045"));
//            params.add(new BasicNameValuePair("ReceivePhoneNo","15921108453"));
//            params.add(new BasicNameValuePair("AddService","1"));
//            params.add(new BasicNameValuePair("OrdPrefix","TB"));
//            params.add(new BasicNameValuePair("WaybillNo",WaybillNo));
//            params.add(new BasicNameValuePair("Cod","0"));
//            params.add(new BasicNameValuePair("Pay","0"));
//            params.add(new BasicNameValuePair("Keep","0"));
//            uriBuilder.addParameters(params);
//            HttpGet httpGet = new HttpGet(uriBuilder.build());
//            response = httpClient.execute(httpGet);
//            HttpEntity entity = response.getEntity();
//            // 使用Apache提供的工具类进行转换成字符串
//            entityStr = EntityUtils.toString(entity, "UTF-8");
//            log.info("----->下单接口访问成功，返回值：" + entityStr);
//        } catch (URISyntaxException e) {
//            System.out.println("Url解析出错");
//            e.printStackTrace();
//        } catch (ClientProtocolException e) {
//            System.out.println("http协议出错");
//            e.printStackTrace();
//        } catch (IOException e) {
//            System.out.println("IO异常");
//            e.printStackTrace();
//        }finally {
//            if (null!=response){
//                try {
//                    response.close();
//                } catch (IOException e) {
//                    System.out.println("释放链接出错");
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return entityStr;
//    }

}
