package com.ctrip.www.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ctrip.www.ServiceUtils.BindService;
import com.ctrip.www.ServiceUtils.MyIntentService;

public class MainActivity extends AppCompatActivity {
    private Button bind,unbind,getServiceStatus;
    private BindService.MyBinder binder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            System.out.println("--Service Connected--");
            binder = (BindService.MyBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            System.out.println("--Service Disconnected--");
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = (Button) findViewById(R.id.bind);
        unbind = (Button) findViewById(R.id.unbind);
        getServiceStatus = (Button) findViewById(R.id.getServiceStatus);


        //创建启动Service的Intent
        final Intent intent = new Intent(this,BindService.class);

        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindService(intent,connection, Service.BIND_AUTO_CREATE);

            }
        });

        unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(connection);
            }
        });

        getServiceStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Service 的count值是:" + binder.getCount(),Toast
                        .LENGTH_SHORT).show();
            }
        });

        final Button intentService = (Button) findViewById(R.id.intentService);
        intentService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this,MyIntentService.class);
                startActivity(intent1);
            }
        });
    }
}
