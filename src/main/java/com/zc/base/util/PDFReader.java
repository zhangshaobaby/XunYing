package com.zc.base.util;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdfwriter.ContentStreamWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.PDFOperator;
import org.apache.pdfbox.util.PDFTextStripper;
  
/** 
 * http://pdfbox.apache.org/ 
 *  
 * @author fish 
 *  
 */  
public class PDFReader {  
      
    public void createHelloPDF(String contents,String path) {  
        PDDocument doc = null;  
        PDPage page = null;  
        try {  
            doc = new PDDocument();  
            page = new PDPage();  
            doc.addPage(page);  
            PDFont font = PDType1Font.HELVETICA_BOLD;  
            //设置information
//            PDDocumentInformation docInfo = doc.getDocumentInformation();
//            docInfo.setAuthor(author);
//            docInfo.setKeywords(keywords);
//            docInfo.setTitle(title);
            PDPageContentStream content = new PDPageContentStream(doc, page);  
            content.beginText();  
            content.setFont(font, 12);  
            content.moveTextPositionByAmount(100, 700);  
            content.drawString(contents);  
            content.endText();  
            content.close();  
            doc.save(path);  
            doc.close();  
        } catch (Exception e) {  
            System.out.println(e);  
        }  
    }  
      
    public void readPDF(String path)  
    {  
        PDDocument helloDocument;  
        try {  
            helloDocument = PDDocument.load(new File(path));  
            PDFTextStripper textStripper = new PDFTextStripper();  
            System.out.println(textStripper.getText(helloDocument));  
            helloDocument.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    public void editPDF(String path,String outputPath,HashMap map,String charSet) {  
        try {  
            // pdfwithText  
            PDDocument helloDocument = PDDocument.load(new File(path));  
            // PDDocument helloDocument = PDDocument.load(new File("D:\\gloomyfish\\hello.pdf"));  
            // int pageCount = helloDocument.getNumberOfPages();  
            PDPage firstPage = (PDPage)helloDocument.getDocumentCatalog().getAllPages().get(0);  
            // PDPageContentStream content = new PDPageContentStream(helloDocument, firstPage);  
            PDStream contents = firstPage.getContents();  
              
            PDFStreamParser parser = new PDFStreamParser(contents.getStream());    
            parser.parse();    
            List tokens = parser.getTokens();    
            for (int j = 0; j < tokens.size(); j++)    
            {    
                Object next = tokens.get(j);    
                if (next instanceof PDFOperator)    
                {    
                    PDFOperator op = (PDFOperator) next;    
                    // Tj and TJ are the two operators that display strings in a PDF    
                    if (op.getOperation().equals("Tj"))    
                    {    
                        // Tj takes one operator and that is the string    
                        // to display so lets update that operator    
                        COSString previous = (COSString) tokens.get(j - 1);    
                        byte[]b = previous.getBytes();
                        String string = previous.getString();
                        System.out.println(new String(b,"ISO-8859-1"));
                        System.out.println(new String(b,"UTF-8"));
                        System.out.println(new String(b,"gbk"));
                        System.out.println(new String(b,"gb2312"));
                        
                        //System.out.println(new String(string.getBytes("ISO8859-1"),"unicode"));
//                        Iterator iterator = map.keySet().iterator();                
//                        while (iterator.hasNext()) {    
//							Object key = iterator.next();    
//							string = string.replaceAll(key.toString(), map.get(key).toString());    
//                        }
                        //Word you want to change. Currently this code changes word "Solr" to "Solr123"    
                        previous.reset();    
                        previous.append(string.getBytes("ISO8859-1"));    
                    }    
                    else if (op.getOperation().equals("TJ"))    
                    {    
                        COSArray previous = (COSArray) tokens.get(j - 1);    
                        for (int k = 0; k < previous.size(); k++)    
                        {    
                            Object arrElement = previous.getObject(k);    
                            if (arrElement instanceof COSString)    
                            {    
                                COSString cosString = (COSString) arrElement;    
                                String string = cosString.getString();
//                                Iterator iterator = map.keySet().iterator();                
//                                while (iterator.hasNext()) {    
//        							Object key = iterator.next();    
//        							string = string.replaceAll(key.toString(), map.get(key).toString());    
//                                }
                                // Currently this code changes word "Solr" to "Solr123"    
                                cosString.reset();    
                                cosString.append(string.getBytes("ISO8859-1"));    
                            }    
                        }    
                    }    
                }  
            }  
            // now that the tokens are updated we will replace the page content stream.    
            PDStream updatedStream = new PDStream(helloDocument);    
            OutputStream out = updatedStream.createOutputStream();    
            ContentStreamWriter tokenWriter = new ContentStreamWriter(out);    
            tokenWriter.writeTokens(tokens);    
            firstPage.setContents(updatedStream);    
            helloDocument.save(outputPath); //Output file name    
            helloDocument.close();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (COSVisitorException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  

    public static String getTxt(File f) throws Exception {   
      String ts="";   
      try{   
	      String temp = "";   
	      PDDocument pdfdocument = PDDocument.load(f);   
	      ByteArrayOutputStream out = new ByteArrayOutputStream();   
	      OutputStreamWriter writer = new OutputStreamWriter(out);  
	      PDFTextStripper stripper = new PDFTextStripper();   
	      stripper.writeText(pdfdocument.getDocument(), writer); 
	      pdfdocument.close();   
	      out.close();   
	      writer.close();   
	      byte[] contents = out.toByteArray();   
	      ts = new String(contents);   
	      System.out.println(f.getName() + "length is:" + contents.length + "\n");   
      }catch(Exception e){   
        e.printStackTrace();   
      }   
      finally{   
        return ts;   
      }   
     }  
    public static void main(String[] args)throws Exception {  
    	
    	System.out.println("start");
    	HashMap map = new HashMap();
    	map.put("北京", "上海");
    	PDFReader pdf = new PDFReader();
//    	String tag = pdf.getTxt(new File("D://LR-JKHT-(2014)-0001072-31-1.pdf"));
//    	System.out.println(tag);
    	//pdf.readPDF("D://LR-JKHT-(2014)-0001072-31-1.pdf");
    	pdf.createHelloPDF("fdsafdsafdsafdsafdsavcxz321", "D://test.pdf");
    	//pdf.editPDF("D://LR-JKHT-(2014)-0001072-31-1.pdf", "D://test.pdf", map, "utf-8");
    	System.out.println("end");
    }  
}  