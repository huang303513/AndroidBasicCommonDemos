package com.ctrip.www.android9seekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    private TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = (TextView) findViewById(R.id.textview1);
        textView2 = (TextView) findViewById(R.id.textview2);
        SeekBar seekBar1 = (SeekBar) findViewById(R.id.seekbar1);
        SeekBar seekBar2 = (SeekBar) findViewById(R.id.seekbar2);
        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);

    }

    @Override
    /**
     * 滑动滚动条的时候,会触发的事件
     */
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == R.id.seekbar1) {
            textView1.setText("seekbar1当前的位置是:" + progress);
        } else {
            textView2.setText("seekbar2当前的位置是:" + progress);
        }
    }

    @Override
    /**
     * 从哪里开始拖动
     */
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.seekbar1) {
            textView1.setText("seekbar1开始拖动");
        }else {
            textView2.setText("seekbar2开始拖动");

        }
    }

    @Override
    /**
     * 从哪里结束拖动
     */
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.seekbar1) {
            textView1.setText("seekbar1停止拖动");
        }else {
            textView2.setText("seekbar2停止拖动");

        }
    }
}
