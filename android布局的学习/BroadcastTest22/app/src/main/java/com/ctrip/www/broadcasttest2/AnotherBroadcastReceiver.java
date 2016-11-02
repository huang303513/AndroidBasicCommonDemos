package com.ctrip.www.broadcasttest2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 项目名称：BroadcastTest2
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/8/24 下午4:44
 * 修改人：huangchengdu
 * 修改时间：16/8/24 下午4:44
 * 修改备注：
 */
public class AnotherBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"receive in AnotherBroadcastReceiver",Toast.LENGTH_SHORT).show();
    }
}
