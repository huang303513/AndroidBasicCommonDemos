package com.ctrip.www.webservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ctrip.www.Utils.ProgressDialogUtils;
import com.ctrip.www.Utils.WebServiceUtils;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CityActivity extends AppCompatActivity {
    private List<String> cityStringList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        init();
    }

    private void init() {
        final ListView mCityList = (ListView) findViewById(R.id.city_list);

        //显示进度条
        ProgressDialogUtils.showProgressDialog(this, "数据加载中...");

        //添加参数
        HashMap<String, String> properties = new HashMap<String, String>();
        properties.put("byProvinceName", getIntent().getStringExtra("province"));

        WebServiceUtils.callWebService(WebServiceUtils.WEB_SERVER_URL, "getSupportCity",
                properties, new WebServiceUtils.WebServiceCallBack() {

            @Override
            public void callBack(SoapObject result) {
                ProgressDialogUtils.dismissProgressDialog();
                if (result != null) {
                    cityStringList = parseSoapObject(result);
                    mCityList.setAdapter(new ArrayAdapter<String>(CityActivity.this, android.R
                            .layout.simple_list_item_1, cityStringList));
                } else {
                    Toast.makeText(CityActivity.this, "获取WebService数据错误", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    /**
     * 解析SoapObject对象
     * @param result
     * @return
     */
    private List<String> parseSoapObject(SoapObject result){
        List<String> list = new ArrayList<String>();
        SoapObject provinceSoapObject = (SoapObject) result.getProperty("getSupportCityResult");
        for(int i=0; i<provinceSoapObject.getPropertyCount(); i++){
            String cityString = provinceSoapObject.getProperty(i).toString();
            list.add(cityString.substring(0, cityString.indexOf("(")).trim());
        }

        return list;
    }
}
