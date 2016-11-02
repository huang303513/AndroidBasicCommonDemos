package com.ctrip.www.broadcastbestpractice;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.os.Build;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

/**
 * 项目名称：BroadcastBestPractice
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/8/24 下午7:03
 * 修改人：huangchengdu
 * 修改时间：16/8/24 下午7:03
 * 修改备注：
 */
public class ForceOfflineReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(final Context context, Intent intent) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle("Warning");
        dialogBuilder.setMessage("you are forced to be offline. please try to login again");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityCollector.finishAll();//摧毁所有活动
                Intent intent = new Intent(context,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);//重启活动
            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        if (Build.VERSION.SDK_INT >=23){
            if (!Settings.canDrawOverlays(context)){
//                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//                context.startActivity(intent);
//                return;
            }else {
                alertDialog.show();
            }
        }else {
            alertDialog.show();
        }
    }
}
