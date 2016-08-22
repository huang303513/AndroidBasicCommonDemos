package com.ctrip.www.android5saxxml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 项目名称：android5使用sax解析xml
 * 类描述：
 * 创建人：huangchengdu
 * 创建时间：16/8/22 下午10:11
 * 修改人：huangchengdu
 * 修改时间：16/8/22 下午10:11
 * 修改备注：
 */
public class MyHandler extends DefaultHandler {
    private HashMap<String,String> map = null;//存储耽搁解析的完整对象
    private List<HashMap<String, String>> list = null;//存储所有的解析对象
    private String currentTag = null;//正在解析的元素的标签
    private String currentValue = null;//解析当前元素的值
    private String nodeName = null;//解析当前的节点名称

    public MyHandler(String nodeName){
        this.nodeName = nodeName;
    }

    public List<HashMap<String, String>> getList() {
        return list;
    }

    @Override
    /**
     * 开始解析文档
     */
    public void startDocument() throws SAXException {
        super.startDocument();
        //当读到第一个开始标签的时候,会触发这个方法
        list = new ArrayList<HashMap<String, String>>();

    }

    @Override
    /**
     * 开始解析元素
     */
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        //当遇到文档的开头的时候,调用这个方法
        if (qName.equals(nodeName)){
            map = new HashMap<String, String>();
        }
        if (attributes != null && map != null){
            for (int i = 0; i< attributes.getLength(); i++){
                map.put(attributes.getQName(i), attributes.getValue(i));
            }
        }
        currentTag = qName;
    }

    @Override
    /**
     * 解析属性
     */
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        //这个方法用来处理xml文件所读取到的内容
        if (currentTag != null && map != null){
            currentValue = new String(ch, start, length);
            if (currentValue != null && !currentValue.trim().equals("") && !currentValue.trim().equals("\n")){
                map.put(currentTag,currentValue);
            }
        }
        currentTag = null;//把当前节点的对应的值和标签设置为空
        currentValue = null;
    }

    @Override
    /**
     * 结束元素解析
     */
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        //遇到结束标签的时候,调用这个方法
        if (qName.equals(nodeName)){
            list.add(map);
            map = null;
        }
    }
}
