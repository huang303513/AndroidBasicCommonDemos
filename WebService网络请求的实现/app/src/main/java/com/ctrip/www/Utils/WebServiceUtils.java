package com.ctrip.www.Utils;

import android.os.Handler;
import android.os.Message;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 项目名称：WebService网络请求的实现
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/8/30 下午2:16
 * 修改人：huangchengdu
 * 修改时间：16/8/30 下午2:16
 * 修改备注：
 */
public class WebServiceUtils {
    public static final String WEB_SERVER_URL = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";
    //含有三个线程的线程池
    private static final ExecutorService executorService = Executors.newFixedThreadPool(3);
    //命名空间
    private static final String NAMESPACE = "http://WebXml.com.cn/";

    /**
     * webservice请求
     * @param url 请求地址
     * @param methodName  方法
     * @param properties 参数
     * @param webServiceCallBack 回调
     */
    public static void callWebService(String url, final String methodName, HashMap<String,String>
            properties, final WebServiceCallBack webServiceCallBack){
        //创建HttpTransportse对象,传递webservice服务器地址
        final HttpTransportSE httpTransportSE = new HttpTransportSE(url);
        //创建soapObject对象
        final SoapObject soapObject = new SoapObject(NAMESPACE,methodName);
        //soapObject添加参数
        if (properties != null){
            for (Iterator<Map.Entry<String,String>> it = properties.entrySet().iterator();it
                    .hasNext();){
                Map.Entry<String,String> entry = it.next();
                soapObject.addProperty(entry.getKey(),entry.getValue());
            }
        }
        //实例化SoapSerializationEnvelope,传入webservice的SOAP协议的版本号
        final SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope
                .VER11);
        //设置是否调用的是.net开发的webservice
        soapEnvelope.setOutputSoapObject(soapObject);
        soapEnvelope.dotNet = true;
        httpTransportSE.debug = true;

        //子线程与主线程同学的handler
        final Handler mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //将返回值回调到callback参数
                webServiceCallBack.callBack((SoapObject) msg.obj);
            }
        };

        //开启线程去访问webservice
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                SoapObject resultSoapObject = null;

                try {
                    httpTransportSE.call(NAMESPACE + methodName, soapEnvelope);
                    if (soapEnvelope.getResponse() != null){
                        //获取服务器响应返回的SoapObject
                        resultSoapObject = (SoapObject) soapEnvelope.bodyIn;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } finally {
                    //将获取的消息利用Handler发送到主线程
                    mHandler.sendMessage(mHandler.obtainMessage(0,resultSoapObject));
                }
            }
        });

    }

    /**
     * 回调接口
     */
    public interface WebServiceCallBack{
        public void callBack(SoapObject result);
    }
}
