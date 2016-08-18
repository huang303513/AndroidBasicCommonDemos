package com.ctrip.www.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class OtherActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        textView = (TextView) this.findViewById(R.id.msg);
        Intent intent = getIntent();
        int age = intent.getIntExtra("age",0);
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");

        textView.setText("age-->" + age + "\n" + "name-->" + name + "\n" + "address-->" + address);
    }
}
