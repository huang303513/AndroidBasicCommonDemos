package com.ctrip.www.android4http;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by huangchengdu on 16/8/19.
 */
public class HttpUtils {
    public static String URL_PATH = "http://forums.androidcentral.com/attachments/photo-contests/159177d1421273674t-weekly-photo-contest-travel-img_20150114_081647723_hdr.jpg";

    public static void saveImageToDisk(){
        InputStream inputStream = getInputStream();
        byte[] data = new byte[1024];
        int len = 0;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(android.os.Environment.getExternalStorageDirectory()+ "/test.png",true);
            while ((len = inputStream.read(data)) != -1){
                fileOutputStream.write(data,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static InputStream getInputStream(){
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(URL_PATH);
            if (url != null){
                httpURLConnection = (HttpURLConnection) url.openConnection();
                //设置超时
                httpURLConnection.setConnectTimeout(30000);
                httpURLConnection.setDoInput(true);
                //表示设置本次http请求使用GET方式
                httpURLConnection.setRequestMethod("GET");
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200){
                    //从服务器获得一个输入流
                    inputStream = httpURLConnection.getInputStream();

                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return  inputStream;
    }

}
