package com.ctrip.www.db;

/**
 * 项目名称：android酷欧天气
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/9/1 下午3:49
 * 修改人：huangchengdu
 * 修改时间：16/9/1 下午3:49
 * 修改备注：
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 封装常用的数据库操作
 */
public class CoolWeatherDB {
    /**
     * 数据库名称
     */
    public static final String DB_NAME = "cool_weather";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;
    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;

    /**
     * 构造函数私有化
     * @param context
     */
    private CoolWeatherDB(Context context){
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context,DB_NAME,null,VERSION);
        //初始化DB
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取CoolWeatherDB实例
     * @param context
     * @return
     */
    public synchronized static CoolWeatherDB getInstance(Context context){
        if (coolWeatherDB == null){
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }
}
