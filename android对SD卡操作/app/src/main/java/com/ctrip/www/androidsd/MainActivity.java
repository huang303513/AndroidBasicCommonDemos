package com.ctrip.www.androidsd;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class MainActivity extends AppCompatActivity {
    final String FILE_NAME = "/crazyit.bin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button read = (Button) findViewById(R.id.read);
        final Button write = (Button) findViewById(R.id.write);
        final EditText edit1 = (EditText) findViewById(R.id.edit1);
        final EditText edit2 = (EditText) findViewById(R.id.edit2);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                write(edit1.getText().toString());
                edit1.setText("");
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit2.setText(read());
            }
        });
    }

    private String read(){
        try {
            if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)){
                File sdCardDir = Environment.getExternalStorageDirectory();
                FileInputStream fis = new FileInputStream(sdCardDir.getCanonicalPath() + FILE_NAME);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                StringBuffer sb = new StringBuffer("");
                String line = null;
                while ((line = br.readLine()) != null){
                    sb.append(line);
                }
                br.close();
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    private void write(String content){
        try {
            if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)){
                File sdCardDir = Environment.getExternalStorageDirectory();
                File targetFile = new File(sdCardDir.getCanonicalPath() + FILE_NAME);
                RandomAccessFile raf = new RandomAccessFile(targetFile,"rw");
                raf.seek(targetFile.length());
                raf.write(content.getBytes());
                raf.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
