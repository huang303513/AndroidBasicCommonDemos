package com.ctrip.www.android4http;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //从服务器获得图片保存到本地
        HttpUtils.saveImageToDisk();

    }
}
