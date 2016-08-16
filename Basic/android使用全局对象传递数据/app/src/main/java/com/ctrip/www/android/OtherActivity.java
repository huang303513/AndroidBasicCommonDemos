package com.ctrip.www.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {
    private MyApp myApp;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);
        textView = (TextView) this.findViewById(R.id.msg);
        myApp = (MyApp) getApplication();
        textView.setText("appname-->>" + myApp.getName());
    }
}
