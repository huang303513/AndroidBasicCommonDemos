package com.ctrip.www.ServiceUtils;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * 项目名称：绑定本地Service并与之通信
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/8/30 下午7:17
 * 修改人：huangchengdu
 * 修改时间：16/8/30 下午7:17
 * 修改备注：
 */
public class MyIntentService extends IntentService {
    public MyIntentService(String name) {
        super(name);
    }
    @Override
    /**
     * IntentService会使用单独的线程来执行该方法的代码
     */
    protected void onHandleIntent(Intent intent) {
        long endTime = System.currentTimeMillis() + 20*1000;//二十秒
        while (System.currentTimeMillis() < endTime){
            synchronized (this){
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d("TAG","耗时任务完成");
    }

}
