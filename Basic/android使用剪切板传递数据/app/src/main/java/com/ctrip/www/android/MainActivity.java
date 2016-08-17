package com.ctrip.www.android;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;


public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //从android系统中调用剪切板的服务
                //                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

                //                String name = "jack";
                //                clipboardManager.setText(name);
                //                Intent intent = new Intent(MainActivity.this, OtherActivity
                // .class);
                //                startActivity(intent);

                MyData myData = new MyData("jack", 23);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                String base64String = "";
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    objectOutputStream.writeObject(myData);
                    base64String = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
                    objectOutputStream.close();
                } catch (Exception e) {

                }
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setText(base64String);
                Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                startActivity(intent);
            }
        });
    }

}
