package com.ctrip.www.fragmenttest;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        /**
         * 在活动力火气fragment对象,从而调用他的方法
         */
//        RightFragment rightFragment = getSupportFragmentManager().findFragmentById(R.id.right_fragment);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                AnotherRightFragment fragment = new AnotherRightFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.right_layout,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            default:
                break;
        }
    }
}
