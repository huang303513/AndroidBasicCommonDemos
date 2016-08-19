package com.ctrip.www.android4post;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> params = new HashMap<String, String>();
        params.put("username","admin");
        params.put("password","123456");
        HttpUtils.sendPostMessage(params,"utf-8");
    }
}
