package www.ctrip.com.listviewarrayadaptersimpleadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private ArrayAdapter<String> mArrayAdapter;
    private SimpleAdapter mSimpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        //新建一个数据适配器
        //参数说明：ArrayAdapter(上下文，当前listView加载的每个列表项对应的布局文件，数据源);
        //适配器加载数据源
        String[]arrayData = {"慕课网1","慕课网2","慕课网3","慕课网4","慕课网5","慕课网6"};
        mArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayData);
        //视图(ListView)加载适配器
        mListView.setAdapter(mArrayAdapter);
    }
}
