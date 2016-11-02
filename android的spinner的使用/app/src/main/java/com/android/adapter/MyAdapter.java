package com.android.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import www.ctrip.com.androidspinner.R;

/**
 * Created by huangchengdu on 16/8/30.
 */
public class MyAdapter {
    public  static List<String> getData(){
        List<String> list = new ArrayList<String>();
        list.add("北京");
        list.add("上海");
        list.add("广州");
        list.add("昆明");
        return  list;
    }
    public static  List<Map<String,Object>> getListMaps(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map1 = new HashMap<String, Object>();
        map1.put("ivLogo", R.drawable.calendar);
        map1.put("applicationName","日历");
        Map<String,Object> map2 = new HashMap<String, Object>();
        map2.put("ivLogo", R.drawable.eoemarket);
        map2.put("applicationName","eoemarket客户端");
        list.add(map1);
        list.add(map2);
        return list;
    }
}
