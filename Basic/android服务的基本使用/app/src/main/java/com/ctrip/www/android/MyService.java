package com.ctrip.www.android;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 项目名称：android服务的基本使用
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/8/27 下午10:39
 * 修改人：huangchengdu
 * 修改时间：16/8/27 下午10:39
 * 修改备注：
 */
public class MyService extends Service {

    class DownloadBinder extends Binder{
        public void startDownload(){
            
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    /***
     * 创建服务的时候调用
     */
    public void onCreate() {
        super.onCreate();
        Log.d("MyService","onCreate executed");
    }
    @Override
    /***
     * 服务启动的时候调用
     */
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","onstartcommand executed");
        return super.onStartCommand(intent, flags, startId);

    }
    @Override
    /***
     * 销毁服务的时候调用
     */
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService","onDestroy executed");
    }
}
