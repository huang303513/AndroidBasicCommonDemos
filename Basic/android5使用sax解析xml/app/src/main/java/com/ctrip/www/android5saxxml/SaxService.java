package com.ctrip.www.android5saxxml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * 项目名称：android5使用sax解析xml
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/8/22 下午10:36
 * 修改人：huangchengdu
 * 修改时间：16/8/22 下午10:36
 * 修改备注：
 */
public class SaxService {
    public static List<HashMap<String ,String >> readXML(InputStream inputStream, String nodeName){
        List<HashMap<String,String>> list = null;
        try {
            //创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();//解析xml
            MyHandler handler = new MyHandler(nodeName);
            parser.parse(inputStream,handler);
            inputStream.close();
            return handler.getList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}
