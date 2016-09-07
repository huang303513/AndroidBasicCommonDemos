package com.bestyc.swiperefreshlayout;

import java.util.ArrayList;
import java.util.List;

import com.bestyc.swiperefreshlayout.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements OnRefreshListener{

	private ListView mListView;
	private ArrayAdapter<String> adapter;
	private List<String> mData;
	private SwipeRefreshLayout mSwipeRefreshLayout;
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			//关闭刷新
			mSwipeRefreshLayout.setRefreshing(false);
			mListView.setAdapter(adapter);
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipRefreshLayout);
		mSwipeRefreshLayout.setOnRefreshListener(this);
		//设置刷新时的颜色
		mSwipeRefreshLayout.setColorSchemeResources(
				android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light);
		mListView=(ListView) findViewById(R.id.listview);
		mData = new ArrayList<String>();
		for (int i=0;i<=10;i++) {
			mData.add("加载的数据"+i);
		}
		adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.text,mData);
	}

	@Override
	public void onRefresh() {//更新数据
		handler.sendEmptyMessageDelayed(1, 3000);
	}
}
