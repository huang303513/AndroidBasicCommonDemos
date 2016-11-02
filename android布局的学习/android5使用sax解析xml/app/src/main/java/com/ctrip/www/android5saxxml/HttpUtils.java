package com.ctrip.www.android5saxxml;

import android.content.ComponentName;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 项目名称：android5使用sax解析xml
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/8/22 下午10:43
 * 修改人：huangchengdu
 * 修改时间：16/8/22 下午10:43
 * 修改备注：
 */
public class HttpUtils {
    public static InputStream getXML(String path){
        InputStream inputStream = null;
        try {
            URL url = new URL(path);
            if (url != null){
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(3000);
                connection.setDoInput(true);//设置输入流
                connection.setRequestMethod("GET");
                int code = connection.getResponseCode();
                if (code == 200){
                    inputStream = connection.getInputStream();
                }
            }
        }catch (Exception e){

        }
        return  inputStream;
    }
}
