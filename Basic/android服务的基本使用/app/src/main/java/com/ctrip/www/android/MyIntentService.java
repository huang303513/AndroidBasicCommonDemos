package com.ctrip.www.android;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * 项目名称：android服务的基本使用
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/8/28 上午11:37
 * 修改人：huangchengdu
 * 修改时间：16/8/28 上午11:37
 * 修改备注：
 */

/**
 * IntentService是一个异步的,会自动停止的服务.
 */
public class MyIntentService extends IntentService {

    public  MyIntentService(){
        super("MyIntentService");//调用父类有参构造函数
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //打印当前线程的id
        Log.d("MyIntentService","Thread id is " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService","onDestroy executed");
    }
}
