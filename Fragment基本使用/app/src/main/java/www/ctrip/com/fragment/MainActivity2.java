package www.ctrip.com.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/11/3 下午11:02
 * 修改备注：
 */
public class MainActivity2 extends Activity {
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        Button button = (Button) findViewById(R.id.buton);
        button.setText("改变");
        tv = (TextView) findViewById(R.id.text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("TextView改变了");
            }
        });
    }
}
