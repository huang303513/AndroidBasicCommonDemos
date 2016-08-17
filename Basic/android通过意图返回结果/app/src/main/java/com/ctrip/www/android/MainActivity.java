package com.ctrip.www.android;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private final static int REQUESTCODE = 1;

    private EditText one,two,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) this.findViewById(R.id.button);
        one = (EditText)this.findViewById(R.id.one);
        two = (EditText)this.findViewById(R.id.two);
        result = (EditText)this.findViewById(R.id.result);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //获取用户输入的值
                int a = Integer.parseInt(one.getText().toString());
                int b = Integer.parseInt(two.getText().toString());
                Intent intent = new Intent(MainActivity.this,OtherActivity.class);
                intent.putExtra("a",a);
                intent.putExtra("b",b);
                //启动activity的时候必须调用这个方法
                startActivityForResult(intent, REQUESTCODE);//表示可以返回结果
            }
        });
    }

    @Override
    //通过这个方法返回intent中返回的值
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2){
            if (requestCode == REQUESTCODE){
                int three =  data.getIntExtra("three",0);
                result.setText(String.valueOf(three));
            }
        }
    }
}
