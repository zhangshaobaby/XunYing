package com.zc.base.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.zc.bsm.pojo.User;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarker {
	public Template getTemplate(String path,String name)
    {
        try
        {
            //通过FreeMarker的Configuration读取相应的FTL
            Configuration conf = new Configuration();       
            conf.setDefaultEncoding("UTF-8");
            //设置去哪里读取相应的ftl模板文件
            conf.setDirectoryForTemplateLoading(new File(path));  
            //在模板文件目录中找到名称为name的文件
            Template temp = conf.getTemplate(name);
            return temp;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
      return null;
    }
	public void implement(Map root,String base,String ftl,String outputPath){
		
		//取得模版文件
		try{
			Template t = this.getTemplate(base,ftl);
			//Template t =cfg.getTemplate("user.ftl");
			FileOutputStream output=new FileOutputStream(outputPath);
			Writer out = new OutputStreamWriter(output, "UTF-8");
			//合并数据模型和模版，并将结果输出到out中
			t.process(root,out);
			out.flush();
			out.close();
		} catch(TemplateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[]args){
		FreeMarker fm = new FreeMarker();
		Map root = new HashMap();
		//放入对应数据key value
		root.put("username","c3");
		root.put("age","26");
		root.put("sex","男");
		
		List list = new ArrayList();
		User u = new User();
		u.setId("1");
		u.setNinkName("nicknawme;ls");
		list.add(u);
		u = new User();
		u.setId("2");
		u.setNinkName(";oiu;ls");
		list.add(u);
		u = new User();
		u.setId("3");
		u.setNinkName("cxzvxz;ls");
		list.add(u);
		u = new User();
		u.setId("4");
		u.setNinkName("fdsafda;ls");
		list.add(u);
		root.put("userList",list);
		fm.implement(root,"D://","user.ftl","D://ddd.html");
	}
}
