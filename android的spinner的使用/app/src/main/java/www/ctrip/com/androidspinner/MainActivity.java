package www.ctrip.com.androidspinner;

import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.android.adapter.MyAdapter;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private Spinner spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list = MyAdapter.getData();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R
                .layout.simple_gallery_item,list);
        spinner.setAdapter(adapter);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<Map<String,Object>> listmaps = MyAdapter.getListMaps();
        SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this,listmaps, R.layout.item,new
                String[]{"ivLogo","applicationName"},new int[]{R.id.imageview, R.id.textview});
        spinner2.setAdapter(simpleAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String appName = ((Map<String,Object>)spinner2.getItemAtPosition(position)).get
                        ("applicationName").toString();
                 setTitle(appName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
