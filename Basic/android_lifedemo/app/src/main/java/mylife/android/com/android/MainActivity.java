package mylife.android.com.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG,"onCreate Method is executed");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart Method is executed");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart Method is executed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume Method is executed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop Method is executed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause Method is executed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy Method is executed");
    }
}
