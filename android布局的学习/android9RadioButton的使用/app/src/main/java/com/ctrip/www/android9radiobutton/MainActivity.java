package com.ctrip.www.android9radiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioGroup group;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        group = (RadioGroup)this.findViewById(R.id.sex);
        button = (Button) this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int len = group.getChildCount();//获得单选按钮组的选项个数
                String msgString = "";
                for (int i = 0; i < len;i++){
                    RadioButton radioButton = (RadioButton) group.getChildAt(i);
                    if (radioButton.isChecked()){
                        msgString = radioButton.getText().toString();
                        break;
                    }
                }
                Toast.makeText(MainActivity.this,msgString,1).show();
            }
        });
    }
}
