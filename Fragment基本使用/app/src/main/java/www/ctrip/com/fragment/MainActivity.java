package www.ctrip.com.fragment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup mGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGroup = (RadioGroup) findViewById(R.id.radiogroup);
        mGroup.setOnCheckedChangeListener(this);
    }

    @Override
    /**
     * RadioGroup点击监听事件
     */
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.first:{
                Intent intent = new Intent(this,MainActivity2.class);
                startActivity(intent);
                break;
            }
            case R.id.second:{
                break;
            }
            case R.id.thrid:{
                break;
            }
            case R.id.fourth:{
                break;
            }

        }
    }
}
