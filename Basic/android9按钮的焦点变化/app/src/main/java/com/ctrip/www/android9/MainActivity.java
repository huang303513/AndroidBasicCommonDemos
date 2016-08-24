package com.ctrip.www.android9;

import android.app.usage.UsageEvents;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener, View.OnFocusChangeListener, View.OnKeyListener{
    private int value = 1;//用于改变按钮的大小
    private Button commonButton;
    private Button imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        commonButton = (Button) this.findViewById(R.id.commonbutton);
        imageButton = (Button) this.findViewById(R.id.imagebutton);
        commonButton.setOnClickListener(this);
        //imageButton.setOnClickListener(this);
        imageButton.setOnTouchListener(this);
        imageButton.setOnFocusChangeListener(this);
        imageButton.setOnKeyListener(this);
    }

    @Override
    /***
     * 点击按钮时触发这个方法
     */
    public void onClick(View view) {
        Button button = (Button)view;
        //当按钮的狂读达到整个屏幕的宽度
        if (value == 1 && button.getWidth()==getWindowManager().getDefaultDisplay().getWidth()){
            value = -1;
        }else if (value == -1 && button.getWidth() < 100){
            value = 1;
        }
        button.setWidth(button.getWidth() + (int)(button.getWidth() * 0.1) * value);
        button.setHeight(button.getHeight() +(int)(button.getHeight() * 0.1) * value);
    }

    @Override
    /***
     * 当控件的焦点发生变化的时候会调用这个方法
     */
    public void onFocusChange(View view, boolean b) {
        if (b) {
            imageButton.setBackgroundResource(R.drawable.button2);
        } else {
            imageButton.setBackgroundResource(R.drawable.button1);
        }
    }

    @Override
    /***
     * 为某个健按下添加事件
     */
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (KeyEvent.ACTION_DOWN == keyEvent.getAction()){
            view.setBackgroundResource(R.drawable.button3);
        } else if (keyEvent.ACTION_UP == keyEvent.getAction()) {
            view.setBackgroundResource(R.drawable.button2);
        }
        return false;
    }

    @Override
    /***
     * 当前显示屏被触摸的时候的回调事件
     */
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            view.setBackgroundResource(R.drawable.button1);
        } else if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            view.setBackgroundResource(R.drawable.button2);
        }
        return false;
    }
}
