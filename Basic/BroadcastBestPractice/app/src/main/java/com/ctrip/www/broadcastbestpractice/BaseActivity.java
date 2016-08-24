package com.ctrip.www.broadcastbestpractice;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

/**
 * 项目名称：BroadcastBestPractice
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/8/24 下午6:28
 * 修改人：huangchengdu
 * 修改时间：16/8/24 下午6:28
 * 修改备注：
 */
public class BaseActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
