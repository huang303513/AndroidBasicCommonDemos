package com.ctrip.www.android5saxxml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String path = "http://192.168.0.102:8080/test.html";
        InputStream inputStream = HttpUtils.getXML(path);
        try {
            List<HashMap<String,String>> list = SaxService.readXML(inputStream,"person");
            for (HashMap<String,String> map:list){
                System.out.println(map.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
