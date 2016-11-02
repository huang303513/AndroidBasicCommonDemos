package com.ctrip.www.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
   // private Button button;
    private Button button;
    private MyApp myApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public  void  onClick(View v){
               myApp = (MyApp)getApplication();
                myApp.setName("jack");//修改之后的名称
                Intent intent = new Intent(MainActivity.this,OtherActivity.class);
                startActivity(intent);
            }
        });
    }
}
