package www.ctrip.com.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/11/3 下午11:05
 * 修改备注：
 */
public class MyFragment extends Fragment {
    /**
     * layout布局文件转换成view
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         * resource:fragment需要加载的布局文件
         * root:加载这个layout的父组件ViewGroup
         * attactToRoot：false 不返回父ViewGroup
         */
        View view = inflater.inflate(R.layout.fragment,container,false);
        TextView text = (TextView) view.findViewById(R.id.text);
        text.setText("静态加载");
        return view;
    }
}
