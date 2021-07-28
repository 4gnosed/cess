/*
 * Copyright (c) 2015-2018 SHENZHEN GUIYI SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
 *
 * 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
/*
 * Copyright (c) 2015-2018 SHENZHEN GUIYI SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
 *
 * 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */

package cn.edu.cess.util;


import cn.edu.cess.result.Result;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;
import java.util.Map;

public class HttpSendUtil {

    public static <T> T doGet(String url, Map<String, String> param, Class<T> respClazz) {
        String s = doGet(url, param);
        return JSONObject.parseObject(s, respClazz);
    }

    public static String doGet(String url, Map<String, String> param) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url) {
        return doGet(url, null);
    }

    public static <T> T doPost(String url, String entityJson, Class<T> respClazz) {
        String s = doPost(url, entityJson);
        return JSONObject.parseObject(s, respClazz);
    }

    public static String doPost(String url, String entityJson) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);

            // 创建请求内容
            StringEntity entity = new StringEntity(entityJson, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }

    public static String doPost(String url, Map<String, String> headerParam, String entityJson) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);

            // 创建请求头列表
            if (headerParam != null) {
                for (String key : headerParam.keySet()) {
                    httpPost.addHeader(key, headerParam.get(key));
                }
            }
            // 创建请求内容
            StringEntity entity = new StringEntity(entityJson, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);


            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String sendPost(String url, Map<String, String> parameters) {
        return doPost(url, JSON.toJSONString(parameters));
    }

    /**
     * 发送带请求头的http,携带BasicAuth凭证
     *
     * @param url
     * @param headerParam
     * @param paramJson
     * @param username
     * @param password
     * @return
     */
    public static String doPostWithBasicAuth(String url, Map<String, String> headerParam, String paramJson,
                                             String username, String password) {
        CloseableHttpClient httpClient = HttpClients.custom()
                .build();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);

            // 创建请求头列表
            if (headerParam != null) {
                for (String key : headerParam.keySet()) {
                    httpPost.addHeader(key, headerParam.get(key));
                }
            }
            // 创建请求内容
            StringEntity entity = new StringEntity(paramJson, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            // 携带basic auth认证
            httpPost.addHeader("Authorization",
                    "Basic " + Base64.getUrlEncoder().encodeToString((username + ":" + password).getBytes()));

            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }


}
