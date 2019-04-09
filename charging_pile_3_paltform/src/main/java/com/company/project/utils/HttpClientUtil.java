package com.company.project.utils;


import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class HttpClientUtil {
	private static final Logger log = LoggerFactory.getLogger("HttpClientUtil");

    public static String post(String url, String json, String contentType, Map<String,Object> head){
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        try {
            StringEntity s = new StringEntity(json.toString(),"UTF-8");
            s.setContentType("application/"+contentType+";charset=utf-8");//发送json数据需要设置contentType

            if(head != null){
                head.forEach((k,v)->{
                    post.setHeader(k,v.toString());
                });
            }
            post.setEntity(s);
            RequestConfig requestConfig = RequestConfig.custom()

                    .setConnectTimeout(5000).setConnectionRequestTimeout(1000)

                    .setSocketTimeout(5000).build();

            post.setConfig(requestConfig);

            CloseableHttpResponse response = httpclient.execute(post);


            HttpEntity entity = response.getEntity();//得到请求回来的数据
            String result=EntityUtils.toString(entity,"UTF-8");
            log.info("得到的结果:" +result);

            return result;
        }catch (Exception e) {
            e.printStackTrace();
            return "{\"code\":400,\"message\":\"请求失败,请稍后再试\"}";
        }


    }
    public static String post(String url,String json,String contentType){
        return post( url,  json,  contentType,null);
    }
	public static String post(String url,String json){
        return post(url, json, "json");
    }
	
	public static String put(String url,String json){
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPut put = new HttpPut(url);
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            put.setEntity(s);
            


            RequestConfig requestConfig = RequestConfig.custom()  

                    .setConnectTimeout(5000).setConnectionRequestTimeout(1000)  

                    .setSocketTimeout(5000).build();  

            put.setConfig(requestConfig);  

            CloseableHttpResponse response = httpclient.execute(put);  


            HttpEntity entity = response.getEntity();//得到请求回来的数据
            String result=EntityUtils.toString(entity,"UTF-8");
            log.info("得到的结果:" +result);
            return result;
        }catch (Exception e) {
        	return "{\"code\":400,\"message\":\"请求失败,请稍后再试\"}";
		}
		
		
	}
	
	public static String get(String url){
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        try {
           
            RequestConfig requestConfig = RequestConfig.custom()  

                    .setConnectTimeout(5000).setConnectionRequestTimeout(1000)  

                    .setSocketTimeout(5000).build();  

            get.setConfig(requestConfig);  

            CloseableHttpResponse response = httpclient.execute(get);
            HttpEntity entity = response.getEntity();//得到请求回来的数据
            String result=EntityUtils.toString(entity,"UTF-8");
            log.info("得到的结果:" +result);


            return result;
        }catch (Exception e) {
        	return "{\"code\":400,\"message\":\"请求失败,请稍后再试\"}";
		}
		
		
	}


}
