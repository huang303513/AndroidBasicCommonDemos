package com.ctrip.www.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {
    private TextView textView;
    public static String name;
    public static int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        textView = (TextView) this.findViewById(R.id.msg);
        textView.setText("name-->" + name +"\n"+ "age-->" + age);
    }
}
