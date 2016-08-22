package com.ctrip.www.android4post;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by huangchengdu on 16/8/19.
 */
public class HttpUtils {
    //请求服务端的URL
    private  static String PATH = "http://localhost/WebStudyDemo";
    private static URL url;

    static {
        try {
            url = new URL(PATH);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param 填写的url参数
     * @param 字节编码
     * @return
     */
    public static String sendPostMessage(Map<String ,String > params, String encode){
        //作为stringbuffer初始化的字符串
        StringBuffer buffer = new StringBuffer(PATH);
        buffer.append("?");
        try {
            if (params != null && !params.isEmpty()){
                for (Map.Entry<String,String> entry: params.entrySet()){
                    buffer.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(),encode)).append("&");

                }
            }
            buffer.deleteCharAt(buffer.length() - 1);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setConnectTimeout(3000);
            urlConnection.setDoInput(true);//表示从服务器获取数据
            urlConnection.setDoOutput(true);//表示向服务器写数据
            //获取上传信息的字节大小以及长度
            byte[] mydata = buffer.toString().getBytes();
            //设置请求体的一些属性,表示设置请求体的类型是文本类型
            urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Content-Length",String.valueOf(mydata.length));
            //获取输出流/向服务器输出数据
            OutputStream outputStream = urlConnection.getOutputStream();
            outputStream.write(mydata);
            
            //System.out.println("TEST" + buffer.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  buffer.toString();
    }
}
