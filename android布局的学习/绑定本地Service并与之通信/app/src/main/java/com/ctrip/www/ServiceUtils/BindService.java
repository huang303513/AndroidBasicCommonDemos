package com.ctrip.www.ServiceUtils;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * 项目名称：绑定本地Service并与之通信
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/8/30 下午5:45
 * 修改人：huangchengdu
 * 修改时间：16/8/30 下午5:45
 * 修改备注：
 */
public class BindService extends Service{
    private int count;
    private boolean quit;
    private MyBinder binder = new MyBinder();

    public class MyBinder extends Binder{
        public int getCount(){
            return count;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("Service is Binded");
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Service is Created");
        //启动一条线程/动态地修改count的值
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (!quit){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("Service is unBinded");
        return super.onUnbind(intent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.quit = true;
        System.out.println("Service is Destroyed");
    }
}
