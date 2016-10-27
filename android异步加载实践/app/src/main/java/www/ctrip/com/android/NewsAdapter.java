package www.ctrip.com.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/10/26 下午10:51
 * 修改备注：
 */
public class NewsAdapter extends BaseAdapter implements AbsListView.OnScrollListener{
    private List<NewsBean> mList;
    private LayoutInflater mInflater;
    private  ImageLoader mImageLoader;
    private int mStart,mEnd;
    public static String[] URLS;//用于保存当前获取的所有图片的url地址
    private boolean mFirstIn;

    public NewsAdapter(Context context, List<NewsBean> data, ListView listView){
        mList = data;
        mInflater = LayoutInflater.from(context);
        mImageLoader = new ImageLoader(listView);

        URLS = new String[data.size()];
        for (int i = 0;i < data.size();i++){
            URLS[i] = data.get(i).newsIconUrl;
        }
        mFirstIn = true;
        //注册对于的滚动事件
        listView.setOnScrollListener(this);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_layout,null);
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ivIcon.setImageResource(R.mipmap.ic_launcher);
        String url = mList.get(position).newsIconUrl;
        viewHolder.ivIcon.setTag(url);
        //加载网络图片
       // new ImageLoader().showImageByThread(viewHolder.ivIcon,url);

        mImageLoader.showImageByAsyncTask(viewHolder.ivIcon,url);
        viewHolder.tvTitle.setText(mList.get(position).newsTitle);
        viewHolder.tvContent.setText(mList.get(position).newsContent);

        return convertView;
    }

    /**
     * 滚动的代理调用。在滚动过程中，不设置view，只有在停止滚动的时候开始加载任务。
     * @param view
     * @param scrollState
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE){
            //加载可见项
            mImageLoader.loadImages(mStart,mEnd);
        }else {
            //停止任务
            mImageLoader.cancelAllTasks();
        }
    }

    /**
     * 初始化的时候会调用
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mStart = firstVisibleItem;
        mEnd = firstVisibleItem + visibleItemCount;
        //第一次启动并且可见item大于零，因为初始的时候会调用多次
        if (mFirstIn && visibleItemCount > 0){
            mImageLoader.loadImages(mStart,mEnd);
            mFirstIn = false;
        }
    }


    class ViewHolder{
        public TextView tvTitle,tvContent;
        public ImageView ivIcon;
    }
}
