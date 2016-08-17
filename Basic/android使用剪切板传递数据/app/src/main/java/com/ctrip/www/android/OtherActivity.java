package com.ctrip.www.android;

import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class OtherActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        textView = (TextView) this.findViewById(R.id.msg);
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        String msgString = clipboardManager.getText().toString();
        //textView.setText(msgString);
        byte[] base64_byte = Base64.decode(msgString,Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(base64_byte);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            MyData myData = (MyData)objectInputStream.readObject();
            textView.setText(myData.toString());
        }catch (Exception e) {


        }
    }
}
