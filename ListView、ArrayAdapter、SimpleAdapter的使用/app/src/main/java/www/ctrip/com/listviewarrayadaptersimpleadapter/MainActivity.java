package www.ctrip.com.listviewarrayadaptersimpleadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener{
    private ListView mListView;
    private ArrayAdapter<String> mArrayAdapter;
    private List<Map<String,Object>> dataList;
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
       // mListView.setAdapter(mArrayAdapter);


        //SimpAdapter适配器参数说明
        //context：上下文
        //data：数据源(List<? extends Map<String,?>> data)一个Map所组成的List集合。每个Map都会对应ListView列表中的一行，每个Map(健值对)
        // 中的健必须包含所有在from中所指的健。
        //resource:列表项中布局文件的ID
        //from:Map中的健名
        //to:绑定数据是同中的ID，与from成对应关系
        dataList = new ArrayList<Map<String,Object>>();
        mSimpleAdapter = new SimpleAdapter(this,getData(), R.layout.item,new String[]{"pic","text"},new int[]{
            R.id.pic, R.id.text});
        mListView.setAdapter(mSimpleAdapter);
        mListView.setOnScrollListener(this);
        mListView.setOnItemClickListener(this);

    }

    private List<Map<String,Object>> getData(){
        for (int i=0; i< 20; i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("pic", R.mipmap.ic_launcher);
            map.put("text","慕课网" + i);
            dataList.add(map);
        }
        return dataList;
    }

    /*
    *
    * */
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){
            case SCROLL_STATE_FLING:
                Log.i("Main","用户再手指离开屏幕之前，由于用力滑了一下，视图仍在滚动");
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("pic", R.mipmap.ic_launcher);
                map.put("text","增加项目");
                dataList.add(map);
                mSimpleAdapter.notifyDataSetChanged();
                break;
            case SCROLL_STATE_IDLE:
                Log.i("Main","视图已经停止滑动");
                break;
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("Mian","手指没有离开屏幕，视图正在滑动");
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    /*
    * item点击监听器
    * */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text = mListView.getItemAtPosition(position) + "";
        Toast.makeText(this,"position=" + position + "text=" + text,Toast.LENGTH_SHORT).show();
    }
}
