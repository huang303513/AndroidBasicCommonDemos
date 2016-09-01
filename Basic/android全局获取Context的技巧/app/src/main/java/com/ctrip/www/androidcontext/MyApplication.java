package com.ctrip.www.androidcontext;

import android.app.Application;
import android.content.Context;

/**
 * 项目名称：android全局获取Context的技巧
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/9/1 下午3:19
 * 修改人：huangchengdu
 * 修改时间：16/9/1 下午3:19
 * 修改备注：
 */

/**
 * Android 提供了一个 Application 类,每当应用程序启动的时候,系统就会自动将这个类 进行初始化。
 */
public class MyApplication extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
    public static Context getContext(){
        return context;
    }
}
