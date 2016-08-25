package com.ctrip.www.android9imageview;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imageview);
        //设置第一个图片的比例大小
        //表示图片的宽度是200,高度100
        imageView.setLayoutParams(new LinearLayout.LayoutParams(200,100));
        setTitle("height:" + imageView.getLayoutParams().height + "--width-->>" + imageView.getLayoutParams().width);
    }
}
