package com.kunlun.erp.core.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HttpUtil
 * @Description HTTP 工具
 * @Author Jm.zhang
 * @Date 2019/11/14 11:25
 * @Version 1.0
 **/
public class HttpUtil {
    private Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 获取域名
     * @param request
     * @return
     */
    public static String getDomain(HttpServletRequest request){
        return request.getServerName();
    }
    /**
     * 功能描述:Map转URL 参数 拼接
     *
     * @param map map参数
     * @return URL 字符串
     */
    public static String toUrlParams(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        if (map.size() > 0) {
            for (String key : map.keySet()) {
                sb.append(key).append("=");
                if (StringUtils.isEmpty(map.get(key))) {
                    sb.append("&");
                } else {
                    String value = map.get(key);
//                    try {
//                        value = URLEncoder.encode(value, "UTF-8");
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
                    sb.append(value).append("&");
                }
            }
        }
        return sb.toString();
    }

    /**
     * 功能描述:字节流获取请求
     * @param request  HTTP请求
     * @return 输入的消息
     */
    public static String getRequestByInputStream(HttpServletRequest request) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder buff = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            buff.append(line);
        }
        return buff.toString();
    }

    //无参数 get
    public String doGet(String uri) {
        HttpGet httpGet = new HttpGet(uri);
        return sendHttpGet(httpGet);
    }

    //json  post
    public String sendMessage(String json, String uri) {
        logger.debug("remote url : " + uri);
        HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader("Content-type", "application/json");
        StringEntity entity = null;
        try {
            entity = new StringEntity(json, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        httpPost.setEntity(entity);//http post with json data
        return sendHttpPost(httpPost);
    }

    //map get
    public String doGet(String uri, List<NameValuePair> parameters) {
        HttpGet httpGet = new HttpGet(uri);
        String param;
        try {
            param = EntityUtils.toString(new UrlEncodedFormEntity(parameters));
            //build get uri with params
            httpGet.setURI(new URIBuilder(httpGet.getURI().toString() + "?" + param).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpGet(httpGet);
    }

    //map post
    public String doPost(String uri, Map<String, String> createMap) {
        HttpPost httpPost = new HttpPost(uri);
        List<NameValuePair> parameters = new ArrayList<>();
        Iterator iterator = createMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
            parameters.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }
        try {
            if (parameters.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(parameters, "UTF-8");
                httpPost.setEntity(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost);
    }

    public String doPost(String uri) {
        HttpPost httpPost = new HttpPost(uri);
        return sendHttpPost(httpPost);
    }

    public String doPost(String uri, String reqXml){
        HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader("Content-Type", "application/xml");
        StringEntity entity = null;
        try {
            entity = new StringEntity(reqXml, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        httpPost.setEntity(entity);//http post with xml data
        return sendHttpPost(httpPost);
    }

    private String sendHttpGet(HttpGet httpGet) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity;
        String responseContent = null;
        try {
            httpClient = HttpClients.createDefault();
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null){
                    response.close();
                }
                if (httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }

    private String sendHttpPost(HttpPost httpPost) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity;
        String responseContent = null;
        try {
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(this.setConfig());
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null)
                    response.close();
                if (httpClient != null)
                    httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return responseContent;
    }

    private RequestConfig setConfig() {
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(50000).setConnectionRequestTimeout(50000).setSocketTimeout(50000).build();
        return requestConfig;
    }

    /**
     * 上传文件
     * @param file_path
     * @param url
     * @return
     */
/*    public  String uploadFile(String file_path,String file_name,String url,Map<String,String> postParam){
        String full_path = file_path+file_name;
        HttpPost httppost = new HttpPost(url);
        httppost.setConfig(this.setConfig());
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        //others param for request
        StringBody stringFileNameBody = new StringBody("fileName", ContentType.create("text/plain", "UTF-8"));
        builder.addPart("stringFileNameBody", stringFileNameBody);
        StringBody stringFileMd5 = new StringBody("md5", ContentType.create("text/plain", "UTF-8"));
        builder.addPart("stringFileMd5", stringFileMd5);
        //file param for request
        File file = new File(full_path);
        FileBody fileBody = new FileBody(file, ContentType.create("multipart/form-data", "UTF-8"));
        builder.addPart("file", fileBody);

        //设计文件以外的参数
        Set<String> keySet = postParam.keySet();
        for (String key : keySet) {
            builder.addPart(key, new StringBody(postParam.get(key), ContentType.create("text/plain","UTF-8")));
        }
        HttpEntity reqEntity = builder.build();
        httppost.setEntity(reqEntity);
        return sendHttpPost(httppost);
    }*/
    /**
     * 根据地址获得数据的字节流
     * @param strUrl 网络连接地址
     * @return
     */
    public static byte[] getFileFromNetByUrl(String strUrl){
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取文件数据
            byte[] btImg = readInputStream(inStream);//得到文件的二进制数据
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 从输入流中获取数据
     * @param inStream 输入流
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    /**
     * 通过url 下载文件 并保存
     * @param url
     * @param file_path
     * @param file_name
     * @return
     */
    public boolean downLoadByUrl(String url,String file_path,String file_name){
        boolean flag = true;
        byte[] file_byte = getFileFromNetByUrl(url);
        try {
            File file = new File(file_path+file_name);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(file_byte);
            fops.flush();
            fops.close();
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }

        return flag;
    }
    public static void main(String[] arg){
        String url ="http://127.0.0.1:8080/admin/resources/insured_file/20190305095126728.xlsx";
        String path = "D:\\idea-wrokspace\\insurance\\insurance-server\\src\\main\\webapp\\resources\\insured_file\\";
        new HttpUtil().downLoadByUrl(url,path,"test.xlsx");

    }

}
