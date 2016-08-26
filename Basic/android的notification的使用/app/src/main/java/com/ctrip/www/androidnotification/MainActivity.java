package com.ctrip.www.androidnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button sendNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendNotice = (Button) findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_notice:
                NotificationManager manager = (NotificationManager) getSystemService
                        (NOTIFICATION_SERVICE);


                Notification.Builder builder1 = new Notification.Builder(MainActivity.this);
                builder1.setSmallIcon(R.mipmap.ic_launcher); //设置图标
                builder1.setTicker("显示第二个通知");
                builder1.setContentTitle("通知"); //设置标题
                builder1.setContentText("点击查看详细内容"); //消息内容
                builder1.setWhen(System.currentTimeMillis()); //发送时间
                builder1.setDefaults(Notification.DEFAULT_ALL); //设置默认的提示音，振动方式，灯光
                builder1.setAutoCancel(true);//打开程序后图标消失
                Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0,
                        intent, 0);
                builder1.setContentIntent(pendingIntent);
                Notification notification1 = builder1.build();
                Uri soundUri = Uri.fromFile(new File("/system/media/audio/ringtones/Basic_tone" +
                        ".ogg"));
                notification1.sound = soundUri;
                manager.notify(124, notification1); // 通过通知管理器发送通知

                default:
                    break;
        }
    }
}
