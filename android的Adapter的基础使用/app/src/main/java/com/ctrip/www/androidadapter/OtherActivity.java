package com.ctrip.www.androidadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class OtherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        //也可以在代码中设置
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array
//                .myarray,android.R.layout.simple_list_item_multiple_choice);
    }
}
