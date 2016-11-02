package com.ctrip.www.broadcastbestpractice;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：BroadcastBestPractice
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/8/24 下午6:25
 * 修改人：huangchengdu
 * 修改时间：16/8/24 下午6:25
 * 修改备注：
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void finishAll(){
        for (Activity activity:activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }
}
