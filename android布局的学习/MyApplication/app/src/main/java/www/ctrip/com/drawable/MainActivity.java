package www.ctrip.com.drawable;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView1 = (TextView) findViewById(R.id.text1);
        TextView textView2 = (TextView) findViewById(R.id.text2);

        //colordrawable的使用
        int mycolor = 0xff1234;
        ColorDrawable drawable = new ColorDrawable(mycolor);
        textView1.setBackground(drawable);

//        int getcolor = Resources.getSystem().getColor(android.R.color.holo_blue_dark,null);
//        textView2.setBackgroundColor(getcolor);
    }
}
