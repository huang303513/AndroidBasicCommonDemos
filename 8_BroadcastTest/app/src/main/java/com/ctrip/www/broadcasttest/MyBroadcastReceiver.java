package com.ctrip.www.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 项目名称：BroadcastTest
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/8/24 下午4:37
 * 修改人：huangchengdu
 * 修改时间：16/8/24 下午4:37
 * 修改备注：
 */
public class MyBroadcastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Received in MyBroddcaseReceiver",Toast.LENGTH_SHORT).show();
        abortBroadcast();//中断广播传播
    }
}
