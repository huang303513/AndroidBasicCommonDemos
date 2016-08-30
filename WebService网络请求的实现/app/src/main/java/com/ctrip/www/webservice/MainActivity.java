package com.ctrip.www.webservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ctrip.www.Utils.ProgressDialogUtils;
import com.ctrip.www.Utils.WebServiceUtils;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> provinceList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        final ListView mProvinceList = (ListView) findViewById(R.id.province_list);
        //显示精度条
        ProgressDialogUtils.showProgressDialog(this, "数据加载中");
        //通过工具类调用webservice接口
        WebServiceUtils.callWebService(WebServiceUtils.WEB_SERVER_URL, "getSupportProvince", null,
                new WebServiceUtils.WebServiceCallBack() {
                    @Override
                    /**
                     * 回调处理
                     */
                    public void callBack(SoapObject result) {
                        ProgressDialogUtils.dismissProgressDialog();
                        if (result != null) {
                            provinceList = parseSoapObject(result);
                            mProvinceList.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                                    android.R.layout.simple_list_item_1, provinceList));
                        } else {
                            Toast.makeText(MainActivity.this, "获取WebService数据错误", Toast
                                    .LENGTH_SHORT).show();
                        }
                    }
                });

        mProvinceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,CityActivity.class);
                intent.putExtra("province",provinceList.get(i));
                startActivity(intent);
            }
        });
    }



    /**
     * 解析SoapObject对象
     *
     * @param result
     * @return
     */
    private List<String> parseSoapObject(SoapObject result) {
        List<String> list = new ArrayList<String>();
        SoapObject provinceSoapObject = (SoapObject) result.getProperty("getSupportProvinceResult");
        if (provinceSoapObject == null) {
            return null;
        }
        for (int i = 0; i < provinceSoapObject.getPropertyCount(); i++) {
            list.add(provinceSoapObject.getProperty(i).toString());
        }
        return list;
    }

}