package com.ctrip.www.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//加载布局文件
        button = (Button) this.findViewById(R.id.button);//
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,OtherActivity.class);
                //意图传递数据
                intent.putExtra("name","张三");
                intent.putExtra("age",21);
                intent.putExtra("address","北京");
                startActivity(intent);
            }
        });
    }
}
