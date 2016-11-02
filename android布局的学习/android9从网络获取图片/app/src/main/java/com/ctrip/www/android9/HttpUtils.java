package com.ctrip.www.android9;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 项目名称：android9从网络获取图片
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/8/25 上午8:39
 * 修改人：huangchengdu
 * 修改时间：16/8/25 上午8:39
 * 修改备注：
 */
public class HttpUtils {
    private final static String URL_PATH = "http://forums.androidcentral" +
            ".com:8080/attachments/photo-contests/159177d1421273674t-weekly-photo-contest-travel" +
            "-img_20150114_081647723_hdr.jpg";

    public static InputStream getImageViewInputStream() throws IOException {
        InputStream inputStream = null;

        URL url = new URL(URL_PATH);
        if (url != null) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            int response_code = httpURLConnection.getResponseCode();
            if (response_code == 200) {
                inputStream = httpURLConnection.getInputStream();
            }
        }
        return inputStream;
    }
}
