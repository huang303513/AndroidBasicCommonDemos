package com.ctrip.www.android;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDoneException;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;
    private  MyService.DownloadBinder downloadBinder;

    private Button startIntentService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            downloadBinder = (MyService.DownloadBinder)iBinder;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService = (Button) findViewById(R.id.start_service);
        stopService = (Button) findViewById(R.id.stop_service);
        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);

        startIntentService = (Button) findViewById(R.id.start_intent_service);
        startIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_service:
                Intent startIntent = new Intent(this,MyService.class);
                startService(startIntent);//启动服务
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this,MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this,MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);//绑定服务
                break;
            case R.id.unbind_service:
                unbindService(connection);//接触绑定
                break;
            case R.id.start_intent_service:
                Log.d("MainActivity","Thread is" + Thread.currentThread());
                Intent intentService = new Intent(this,MyIntentService.class);
                startService(intentService);
                break;
            default:
                break;
        }
    }
}
