package com.ctrip.www.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class OtherActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        button = (Button) this.findViewById(R.id.button2);
        textView = (TextView)this.findViewById(R.id.msg);
        editText = (EditText)this.findViewById(R.id.three);
        Intent intent = getIntent();
        int a = intent.getIntExtra("a",0);
        int b = intent.getIntExtra("b",0);
        textView.setText(a+" + " + b +" = " + "?");
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                int three = Integer.parseInt(editText.getText().toString());
                intent.putExtra("three",three);
                //通过Intent对象返回结果,2必须和MainActivity的requestcode保持一致
                setResult(2,intent);
                finish();//结束安全的Activity的生命周期

            }
        });
    }
}
