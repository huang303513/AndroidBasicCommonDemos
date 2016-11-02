package com.ctrip.www.android9checkbox;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    private List<CheckBox> checkBoxs = new ArrayList<CheckBox>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //动态加载布局文件
        String[] checkboxText = new String[]{"您是学生吗?", "是否喜欢android?", "您喜欢旅游吗?", "您打算出国吗?"};
        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_main, null);
        //给指定checkbox赋值
        for (int i = 0; i < checkboxText.length; i++) {
            //先获得checkbox的xml对象
            CheckBox checkBox = (CheckBox) getLayoutInflater().inflate(R.layout.checkbox,null);
            checkBoxs.add(checkBox);
            checkBoxs.get(i).setText(checkboxText[i]);
            //在main的线性布局中,通过for循环添加checkbox
            linearLayout.addView(checkBox,i);
        }
        setContentView(linearLayout);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String s = "";
        for (CheckBox checkBox:checkBoxs){
            if (checkBox.isChecked()){
                s +=checkBox.getText() + "\n";
            }
        }
        if ("".equals(s)){
            s = "没有选中选项";
        }
        new AlertDialog.Builder(this).setMessage(s).setPositiveButton("关闭",null).show();
    }
}
