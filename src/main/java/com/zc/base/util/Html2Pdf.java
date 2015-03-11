package com.zc.base.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;

public class Html2Pdf {
	public boolean convertHtmlToPdf(String inputFile, String outputFile) throws Exception {
        OutputStream os = new FileOutputStream(outputFile);    
        ITextRenderer renderer = new ITextRenderer();    
        String url = new File(inputFile).toURI().toURL().toString();
        renderer.setDocument(url);  
         
        // 解决中文支持问题    
        ITextFontResolver fontResolver = renderer.getFontResolver();   
        //fontResolver.addFont("C:/Windows/Fonts/SIMSUN.TTC", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);    
        fontResolver.addFont("/usr/share/fonts/my_fonts/simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);    
        //解决图片的相对路径问题
        renderer.getSharedContext().setBaseURL("file:/d:/");
        renderer.layout();
        renderer.createPDF(os); 
        
        os.flush();
        os.close();
        return true;
    }
	public static void main(String[]args){
		try{
			new Html2Pdf().convertHtmlToPdf("E:\\apache-tomcat-6.0.41\\webapps\\openMvc\\WEB-INF\\contract\\57\\402881e5479a65f401479a6e67a00000.html",
					"E:\\apache-tomcat-6.0.41\\webapps\\openMvc\\WEB-INF\\contract\\57\\402881e5479a65f401479a6e67a00000.pdf");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
