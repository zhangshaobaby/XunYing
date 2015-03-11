package  com.zc.base.util;

                                                                        
import java.io.FileOutputStream;                                                                                    
import java.io.IOException;                                                                                         
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
                                                                                                                    
import com.itextpdf.text.BaseColor;                                                                                 
import com.itextpdf.text.Document;                                                                                  
import com.itextpdf.text.DocumentException;                                                                         
import com.itextpdf.text.Font;                                                                                      
import com.itextpdf.text.Paragraph;                                                                                 
import com.itextpdf.text.pdf.BaseFont;                                                                              
import com.itextpdf.text.pdf.PdfWriter;                                                                             
import com.zc.bsm.pojo.LuckyMoney;



public class PdfTools {
 /**
  *
  * 生成PDF的方法
  *
  * @return boolean
 * @throws DocumentException 
 * @throws IOException 
  *
  */

 public static boolean createPDF(String pdfPath,Page page) throws DocumentException, IOException {
	 Document doc = new Document();                                                                                                  
	 PdfWriter.getInstance(doc, new FileOutputStream(pdfPath));                                                               
	 doc.open();                                                                                                                     
	
//	 BaseFont bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	 BaseFont bfChinese = BaseFont.createFont("/usr/share/fonts/my_fonts/simsun.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                                  
	 Font fontChinese =  new  Font(bfChinese  ,  12 , Font.NORMAL, BaseColor.BLACK);                                                 
	                                                                                                                                 
	 Paragraph pf = new Paragraph(); 
	 pf.add(new Paragraph("真实姓名 | 身份证 | 手机号 | 时间 | 购买/还款金额/红包",fontChinese));      
	
		for(Map tender : (List<Map>)page.getResult()){
			String temp=tender.get("realName")+" | "+tender.get("identification")+" | "+tender.get("phone")+" | "+tender.get("createTime")+" | "+tender.get("transAmt")+"/"+tender.get("repayAmt")+"/"+tender.get("luckyMoney");
			pf.add(new Paragraph(temp,fontChinese));          
		}
                                                                                              
	 doc.add(pf);                                                                                                                    
	 doc.close();                                                                                                                            

	return true;  
 }                                                                                         



 /**
  *
  * @param args
 * @throws IOException 
 * @throws DocumentException 
  *
  */

 public static void main(String[] args) throws DocumentException, IOException {



 }

}
