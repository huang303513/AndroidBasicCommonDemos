package com.ctrip.www.android;

/**
 * Created by huangchengdu on 16/8/16.
 */
import android.app.Application;

public class MyApp extends Application{
    public String name;

    @Override
    public void onCreate() {
        super.onCreate();
        setName("张三");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
