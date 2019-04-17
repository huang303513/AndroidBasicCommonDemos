package com.coolweather.a16_notificationtest;


import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private NotificationManager mManager;
    public static final String sID = "channel_1";
    public static final String sName = "channel_name_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendNotice = findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_notice:
                NotificationUtil notificationUtil = new NotificationUtil(this);
                notificationUtil.sendNotification("我是原生的通知栏标题","我是原生的通知栏内容");
                break;
            default:
                break;


        }
    }
}
