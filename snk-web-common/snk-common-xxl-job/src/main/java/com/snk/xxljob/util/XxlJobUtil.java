package com.snk.xxljob.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *     参考xxl-job-admin 2.3.0 版本的接口 如果版本不是这个版本请重新核对接口
 * </p>
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 任务调度工具类
 * @Date : 2021/10/29
 */
public class XxlJobUtil {
    public static Logger logger =  LoggerFactory.getLogger(XxlJobUtil.class);

    private static String cookie = "";

    /**
     * 新增
     * @author Cai.ChangJun
     * @param url xxl-admin地址
     * @param requestInfo 添加的任务信息
     * @return:
     * @version 1.0.0
     * @Date 2021/10/29 13:45
     */
    public static JSONObject addJob(String url, JSONObject requestInfo) throws IOException {
        String path = "/jobinfo/add";
        //目标请求地址
        String targetUrl = url + path;
        HttpClient httpClient = new HttpClient();
        PostMethod post = new PostMethod(targetUrl);
        post.setRequestHeader("cookie", cookie);
        List<NameValuePair> nameValuePairs = setPostParams(requestInfo);
        NameValuePair[] nameValuePairs1 = new NameValuePair[nameValuePairs.size()];
        NameValuePair[] nameValuePairs2 = nameValuePairs.toArray(nameValuePairs1);
        post.setRequestBody(nameValuePairs2);
        httpClient.executeMethod(post);
        JSONObject result = new JSONObject();
        result = getJsonObject(post, result);
        return result;
    }

    /**
     * 编辑任务
     * @author Cai.ChangJun
     * @param url xxl-admin地址
     * @param requestInfo
     * @return:
     * @version 1.0.0
     * @Date 2021/10/29 14:21
     */
    public static JSONObject updateJob(String url, JSONObject requestInfo) throws IOException {
        String path = "/jobinfo/update";
        String targetUrl = url + path;
        HttpClient httpClient = new HttpClient();
        PostMethod post = new PostMethod(targetUrl);
        post.setRequestHeader("cookie", cookie);
        List<NameValuePair> nameValuePairs = setPostParams(requestInfo);
        NameValuePair[] nameValuePairs1 = new NameValuePair[nameValuePairs.size()];
        NameValuePair[] nameValuePairs2 = nameValuePairs.toArray(nameValuePairs1);
        post.setRequestBody(nameValuePairs2);
        httpClient.executeMethod(post);
        JSONObject result = new JSONObject();
        result = getJsonObject(post, result);
        return result;
    }

    /**
     * 删除任务
     * @author Cai.ChangJun
     * @param url xxl-admin地址
     * @param id 任务id
     * @return:
     * @version 1.0.0
     * @Date 2021/10/29 14:21
     */
    public static JSONObject deleteJob (String url, int id) throws IOException {
        String path = "/jobinfo/remove?id="+id;
        return doGet(url,path);
    }

    /**
     * 开始任务
     * @author Cai.ChangJun
     * @param url xxl-admin地址
     * @param id 任务id
     * @return:
     * @version 1.0.0
     * @Date 2021/10/29 14:21
     */
    public static JSONObject startJob (String url, int id) throws IOException {
        String path = "/jobinfo/start?id="+id;
        return doGet(url,path);
    }

    /**
     * 停止任务
     * @author Cai.ChangJun
     * @param url xxl-admin地址
     * @param id 任务id
     * @return:
     * @version 1.0.0
     * @Date 2021/10/29 14:21
     */
    public static JSONObject stopJob (String url, int id) throws IOException {
        String path = "/jobinfo/stop?id="+id;
        return doGet(url,path);
    }

    /**
     * 根据xxl-appname获取对应id
     * @author Cai.ChangJun
     * @param url
     * @param appnameParam
     * @return:
     * @version 1.0.0
     * @Date 2021/10/29 14:53
     */
    public static JSONObject getAppNameIdByAppname (String url, String appnameParam) throws IOException {
        String path = "/jobgroup/pageList?appname="+appnameParam;
        return doGet(url,path);
    }

    public static String login(String url,String userName,String password) throws IOException {
        String path = "/login?userName="+userName+"&password="+password;
        String targetUrl = url + path;
        HttpClient httpClient = new HttpClient();
        HttpMethod post = new PostMethod(targetUrl);
        httpClient.executeMethod(post);
        if (post.getStatusCode() == 200) {
            Cookie[] cookies = httpClient.getState().getCookies();
            StringBuffer tmpcookies = new StringBuffer();
            for (Cookie c : cookies) {
                tmpcookies.append(c.toString() + ";");
            }
            cookie = tmpcookies.toString();
        } else {
            try {
                cookie = "";
            } catch (Exception e) {
                cookie="";
            }
        }
        return cookie;
    }


    private static JSONObject getJsonObject(HttpMethod get, JSONObject result) throws IOException {
        InputStream inputStream = get.getResponseBodyAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();
        String str = "";
        while ((str = br.readLine()) != null) {
            stringBuffer.append(str);
        }
        if (get.getStatusCode() == 200) {
            /**
             *  使用此方式会出现
             *  Going to buffer response body of large or unknown size. Using getResponseBodyAsStream instead is recommended.
             *  异常
             *  String responseBodyAsString = get.getResponseBodyAsString();
             *  result = JSONObject.parseObject(responseBodyAsString);
             */
            result = JSONObject.parseObject(stringBuffer.toString());
        } else {
            try {
                result = JSONObject.parseObject(stringBuffer.toString());
            } catch (Exception e) {
                result.put("error", stringBuffer.toString());
            }
        }
        return result;
    }

    public static JSONObject doGet(String url, String path) throws HttpException, IOException {
        String targetUrl = url + path;
        HttpClient httpClient = new HttpClient();
        HttpMethod get = new GetMethod(targetUrl);
        get.setRequestHeader("cookie", cookie);
        httpClient.executeMethod(get);
        JSONObject result = new JSONObject();
        result = getJsonObject(get, result);
        return result;
    }



    public static List<NameValuePair> setPostParams(JSONObject params) {
        try {
            List<NameValuePair> nvps = new ArrayList<>();
            Set<String> keySet = params.keySet();
            for (String key : keySet) {
                nvps.add(new NameValuePair(key, new String(String.valueOf(params.get(key)).getBytes(),"UTF-8")));
            }
           return nvps;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
