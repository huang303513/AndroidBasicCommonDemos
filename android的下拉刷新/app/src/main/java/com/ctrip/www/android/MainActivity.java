package com.ctrip.www.android;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> data;
    private SwipeRefreshLayout swipeRefreshLayout;

    Handler handler = new Handler(){
      public void handleMessage(Message msg){
          //停止刷新
         swipeRefreshLayout.setRefreshing(false);
          //更新数据
          listView.setAdapter(adapter);
      } ;
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,android.R.color
                .holo_green_dark,android.R.color.holo_orange_light);

        listView = (ListView) findViewById(R.id.listview);
        data = new ArrayList<String>();
        for (int i = 0;i <=10; i++){
            data.add("刷新数据"+i);
        }
        adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.text,data);
    }

    @Override
    /**
     * 触发下拉刷新,5秒以后停止刷新
     */
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(1,5000);
    }
}
