package com.ctrip.www.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 项目名称：酷欧天气
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/9/1 下午10:28
 * 修改人：huangchengdu
 * 修改时间：16/9/1 下午10:28
 * 修改备注：
 */


public class HttpUtil {
    public static void sendHttpRequest(final String address, final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    if (listener != null){
                        //回调onFinish方法
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null){
                        //回调onError方法
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
