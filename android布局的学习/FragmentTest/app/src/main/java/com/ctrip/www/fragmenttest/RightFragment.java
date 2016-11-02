package com.ctrip.www.fragmenttest;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class RightFragment extends Fragment {


    public RightFragment() {
        // Required empty public constructor
    }


    @Override
    /**
     * 创建试图的时候调用
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
         * 在fragment里面获取对于的活动,从而调用活动的方法
         */
        MainActivity activity = (MainActivity) getActivity();

        View view = inflater.inflate(R.layout.right_fragment,container,false);
        return view;
    }

    @Override
    /**
     * fragment和活动建立关联的时候调用
     */
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    /**
     * fragment和活动接触关联的时候调用
     */
    public void onDetach() {
        super.onDetach();
    }

    @Override
    /**
     * 与fragment关联的活动创建完毕的时候
     */
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    /**
     * 试图被移除的时候调用
     */
    public void onDestroy() {
        super.onDestroy();
    }
}
