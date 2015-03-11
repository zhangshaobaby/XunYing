package com.zc.base.util;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.zc.bsm.pojo.SoftUpdate;


public class XmlUtil {
    
    public static List<SoftUpdate> readXMLFile(String urladd){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        List<SoftUpdate> list = new ArrayList<SoftUpdate>();
        try {
            builder = dbf.newDocumentBuilder();
            URL url = new URL(urladd); 
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
            Document doc = builder.parse(conn.getInputStream()); // 获取到xml文件
            // 下面开始读取
            Element root = doc.getDocumentElement(); // 获取根元素
            NodeList students = root.getElementsByTagName("soft");
            
            for (int i = 0; i < students.getLength(); i++) {
             // 一次取得每一个元素
             Element ss = (Element) students.item(i);

             // 创建一个实例
             SoftUpdate stu = new SoftUpdate();
             stu.setType(ss.getAttribute("type"));
             stu.setName(ss.getAttribute("name"));
             stu.setVersion(ss.getAttribute("version"));
             stu.setFilepath(ss.getAttribute("filepath"));
             stu.setMsg(ss.getTextContent());
             list.add(stu);
            }
        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (SAXException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        
        
        return list;
    }
    
    public static void main(String[] args){
        List<SoftUpdate> list = readXMLFile("D:/upsoft.xml");
        System.out.println("11111"+list);
        System.out.println("11111");
    }
    
}
